package m.a.nobahar.ui.splash.screen

import m.a.nobahar.domain.model.LoadableData
import m.a.nobahar.domain.model.NotLoaded
import m.a.nobahar.domain.repository.SplashRepository
import m.a.nobahar.ui.shared.BaseViewModel

class SplashViewModel(
    private val splashRepository: SplashRepository
) : BaseViewModel<LoadableData<Unit>>(NotLoaded) {

    init {
        getSplash()
    }

    fun retryClicked() {
        getSplash()
    }

    private fun getSplash() {
        executeLoadable(
            currentValue = state.value,
            action = {
                splashRepository.getSplash()
            },
            data = {
                updateState { it }
            }
        )
    }
}