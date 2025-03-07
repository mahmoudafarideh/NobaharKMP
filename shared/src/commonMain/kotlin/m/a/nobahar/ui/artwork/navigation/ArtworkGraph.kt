package m.a.nobahar.ui.artwork.navigation

import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.coroutines.launch
import m.a.nobahar.ui.artwork.screen.ArtworkScreen
import m.a.nobahar.ui.artwork.screen.ArtworkViewModel
import m.a.nobahar.ui.capture.rememberCaptureController
import m.a.nobahar.ui.goToMatnnegarMarket
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.artworkGraph() {
    composable<ArtworkRoute> {
        val viewModel: ArtworkViewModel = koinViewModel()
        val state = viewModel.state.collectAsStateWithLifecycle().value
        val captureController = rememberCaptureController()
        val coroutineScope = rememberCoroutineScope()
        val argument: ArtworkRoute = it.toRoute()
        ArtworkScreen(
            firstVerse = argument.first.text,
            secondVerse = argument.second.text,
            poetName = argument.poetName,
            state = state,
            onTabClick = { tab ->
                viewModel.tabClicked(tab)
            },
            onFontClick = { font ->
                viewModel.fontClicked(font)
            },
            onFontSizeChange = { size ->
                viewModel.fontSizeChanged(size)
            },
            onColorClick = { color ->
                viewModel.colorClicked(color)
            },
            onSaveButtonClick = {
                viewModel.saveButtonClicked()
                coroutineScope.launch {
                    val bitmapAsync = captureController.captureAsync()
                    try {
                        viewModel.bitmapLoaded(bitmapAsync.await())
                    } catch (_: Throwable) {
                        viewModel.savingFailed()
                    }
                }
            },
            onBackgroundClick = { drawableResource ->
                viewModel.backgroundClicked(drawableResource)
            },
            captureController = captureController,
            onMatnnegarClick = goToMatnnegarMarket()
        )
    }
}