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
import m.a.nobahar.domain.model.MediaPlayerState
import m.a.nobahar.domain.model.PoemAudioInfo
import m.a.nobahar.domain.repository.MediaPlayerRepository
import org.w3c.dom.HTMLAudioElement

class MediaPlayerRepositoryImp : MediaPlayerRepository {
    private var audioElement: HTMLAudioElement? = null
    private var poemAudioInfo: PoemAudioInfo? = null
    override var state: MutableStateFlow<MediaPlayerState?> = MutableStateFlow(null)

    private val job = SupervisorJob()
    private val coroutineScope = CoroutineScope(job + Dispatchers.Default)

    init {
        observePlayerProgressChange()
    }

    private fun setupAudioElement() {
        if (audioElement == null) {
            audioElement = document.createElement("audio") as HTMLAudioElement
            audioElement?.onerror = { _, _, _, _, _ ->
                updateState(MediaPlayerState.LoadingFailed)
            }
            audioElement?.onplay = {
                poemAudioInfo?.let {
                    updateState(MediaPlayerState.Playing(it, getPlayingTime()))
                }
            }
            audioElement?.onpause = {
                poemAudioInfo?.let {
                    updateState(MediaPlayerState.Paused(it))
                }
            }
            audioElement?.onended = {
                updateState(MediaPlayerState.Ended)
            }
            audioElement?.onwaiting = {
                poemAudioInfo?.let {
                    updateState(MediaPlayerState.Loading(it))
                }
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
                            emit(MediaPlayerState.Playing(recitation, getPlayingTime()))
                        }
                        delay(1_000)
                    }
                }
            }.collect {
                state.value = it
            }
        }
    }

    private fun getPlayingTime(): Int? {
        return audioElement?.currentTime?.toInt()
    }

    override fun play(poemAudioInfo: PoemAudioInfo) {
        if (this.poemAudioInfo == poemAudioInfo && audioElement?.paused == true) {
            audioElement?.play()
            return
        }

        this.poemAudioInfo = poemAudioInfo
        setupAudioElement()
        audioElement?.src = poemAudioInfo.recitation.mp3Url
        audioElement?.play()
    }

    override fun pause() {
        audioElement?.pause()
    }

    override fun release() {
        poemAudioInfo = null
        audioElement?.pause()
        audioElement?.src = ""
        audioElement = null
        state.update { null }
    }

    override fun play() {
        audioElement?.play()
    }
}
