package m.a.nobahar.repository

import kotlinx.browser.document
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
import m.a.nobahar.ui.logInfo
import org.w3c.dom.HTMLAudioElement

class MediaPlayerRepositoryImp(
    private val audioSyncHelper: AudioSyncHelper
) : MediaPlayerRepository {
    private var audioElement: HTMLAudioElement? = null
    private var poemAudioInfo: PoemAudioInfo? = null
    override var state: MutableStateFlow<MediaPlayerState?> = MutableStateFlow(null)

    private val job = SupervisorJob()
    private val coroutineScope = CoroutineScope(job + Dispatchers.Default)
    private var audioSync: List<Pair<Int, Int>> = emptyList()

    init {
        observePlayerProgressChange()
    }

    private fun setupAudioElement() {
        if (audioElement == null) {
            audioElement = document.createElement("audio") as HTMLAudioElement
            audioElement?.onwaiting = {
                poemAudioInfo?.let { audioInfo ->
                    if (state.value?.poemAudioInfo == audioInfo && state.value is MediaPlayerState.Playing) {
                        return@let
                    }
                    updateState(MediaPlayerState.Loading(audioInfo))
                }
            }
            audioElement?.onerror = { _, _, _, _, _ ->
                updateState(MediaPlayerState.LoadingFailed)
            }
            audioElement?.onpause = {
                poemAudioInfo?.let { audioInfo ->
                    updateState(MediaPlayerState.Paused(audioInfo))
                }
            }
            audioElement?.onplay = {
                poemAudioInfo?.let { audioInfo ->
                    updateState(MediaPlayerState.Playing(audioInfo, getPlayingVerseIndex()))
                }
            }
            audioElement?.onended = {
                updateState(MediaPlayerState.Ended)
            }
        }
    }

    private fun updateState(newState: MediaPlayerState?) {
        state.update { newState }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun observePlayerProgressChange() {
        coroutineScope.launch {
            state.flatMapLatest {
                flow {
                    while (it is MediaPlayerState.Playing) {
                        poemAudioInfo?.let { recitation ->
                            logInfo("SXO", "$recitation ${getPlayingVerseIndex()}")
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


    private fun getPlayingVerseIndex(): Int? = audioSync.lastOrNull { (_, time) ->
        time <= audioElement!!.currentTime.toInt() + 1_200
    }?.first


    override fun play(poemAudioInfo: PoemAudioInfo) {
        if (this.poemAudioInfo == poemAudioInfo && audioElement?.paused == true) {
            audioElement?.play()
            return
        }

        this.poemAudioInfo = poemAudioInfo
        setupAudioElement()
        audioElement?.src = poemAudioInfo.recitation.mp3Url

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
                        audioElement?.play()
                    }
                }
            } ?: run {
                withContext(Dispatchers.Main) {
                    audioElement?.play()
                }
            }
        }
    }

    override fun pause() {
        audioElement?.pause()
    }

    override fun release() {
        poemAudioInfo = null
        audioElement?.pause()
        audioSync = emptyList()
        audioElement?.src = ""
        audioElement = null
        state.update { null }
    }

    override fun play() {
        audioElement?.play()
    }
}
