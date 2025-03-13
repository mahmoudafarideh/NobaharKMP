package m.a.nobahar.repository

import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import m.a.nobahar.api.helper.AudioSyncHelper
import m.a.nobahar.domain.model.MediaPlayerState
import m.a.nobahar.domain.model.PoemAudioInfo
import m.a.nobahar.domain.repository.MediaPlayerRepository

@OptIn(ExperimentalCoroutinesApi::class)
class MediaPlayerRepositoryImp(
    private val exoPlayer: ExoPlayer,
    private val audioSyncHelper: AudioSyncHelper
) : MediaPlayerRepository {

    private var poemAudioInfo: PoemAudioInfo? = null
    private var audioSync: List<Pair<Int, Int>> = emptyList()
    override var state: MutableStateFlow<MediaPlayerState?> = MutableStateFlow(null)

    private val job = SupervisorJob()
    private val coroutineScope = CoroutineScope(job + Dispatchers.IO)

    init {
        observePlayerProgressChange()
        observeExoPlayerChanges()
    }

    private fun observeExoPlayerChanges() {
        exoPlayer.addListener(object : Player.Listener {

            override fun onPlayerError(error: PlaybackException) {
                super.onPlayerError(error)
                state.update { MediaPlayerState.LoadingFailed }
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                super.onIsPlayingChanged(isPlaying)
                if (!isPlaying) {
                    poemAudioInfo?.let { recitation ->
                        state.update {
                            MediaPlayerState.Paused(recitation)
                        }
                    }
                } else {
                    poemAudioInfo?.let { recitation ->
                        state.update {
                            MediaPlayerState.Playing(
                                recitation, getPlayingVerseIndex()
                            )
                        }
                    }
                }
            }

            override fun onIsLoadingChanged(isLoading: Boolean) {
                super.onIsLoadingChanged(isLoading)
                if (isLoading && !exoPlayer.isPlaying) {
                    poemAudioInfo?.let { recitation ->
                        state.update {
                            MediaPlayerState.Loading(recitation)
                        }
                    }
                }
            }

            override fun onPlaybackStateChanged(playbackState: Int) {
                super.onPlaybackStateChanged(playbackState)
                if (playbackState == Player.STATE_ENDED) {
                    poemAudioInfo?.let { recitation ->
                        state.update {
                            MediaPlayerState.Ended
                        }
                        poemAudioInfo = null
                    }
                }
            }

        })
    }

    private fun getPlayingVerseIndex(): Int? = audioSync.lastOrNull { (_, time) ->
        time <= exoPlayer.currentPosition + 1_200
    }?.first

    private fun observePlayerProgressChange() {
        coroutineScope.launch(Dispatchers.Main) {
            state.flatMapLatest {
                flow {
                    while (it is MediaPlayerState.Playing) {
                        poemAudioInfo?.let { recitation ->
                            emit(MediaPlayerState.Playing(recitation, getPlayingVerseIndex()))
                        }
                        delay(1_000)
                    }
                }
            }.collect {
                state.value = it
            }
        }
    }

    override fun play(poemAudioInfo: PoemAudioInfo) {
        if (this.poemAudioInfo == poemAudioInfo) {
            if (!exoPlayer.isPlaying) {
                exoPlayer.play()
            }
            return
        }
        this@MediaPlayerRepositoryImp.poemAudioInfo = poemAudioInfo
        exoPlayer.apply {
            resetMusicPlayer()
            val mediaItem = MediaItem.fromUri(poemAudioInfo.recitation.mp3Url)
            setMediaItem(mediaItem)
            prepare()
        }
        coroutineScope.launch {
            poemAudioInfo.recitation.syncUrl?.let {
                state.update {
                    MediaPlayerState.Loading(poemAudioInfo)
                }
                runCatching {
                    audioSyncHelper.getAudioSync(it)
                }.onFailure {
                    this@MediaPlayerRepositoryImp.poemAudioInfo = null
                    state.update { MediaPlayerState.LoadingFailed }
                }.onSuccess {
                    audioSync = it
                    withContext(Dispatchers.Main) {
                        exoPlayer.play()
                    }
                }
            } ?: run {
                withContext(Dispatchers.Main) {
                    exoPlayer.play()
                }
            }
        }

    }

    private fun ExoPlayer.resetMusicPlayer() {
        kotlin.runCatching { stop() }
    }

    @androidx.annotation.OptIn(UnstableApi::class)
    override fun release() {
        poemAudioInfo = null
        audioSync = emptyList()
        kotlin.runCatching {
            exoPlayer.resetMusicPlayer()
        }
        state.update { null }
    }

    override fun pause() {
        exoPlayer.pause()
    }

    override fun play() {
        poemAudioInfo?.let {
            exoPlayer.play()
        }
    }
}