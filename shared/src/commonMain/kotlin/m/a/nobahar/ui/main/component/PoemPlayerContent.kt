package m.a.nobahar.ui.main.component


import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import m.a.nobahar.ui.KeepScreenOn
import m.a.nobahar.ui.LocalNavController
import m.a.nobahar.ui.main.PoemPlayerViewModel
import m.a.nobahar.ui.main.model.MediaPlayerUiModel
import m.a.nobahar.ui.poem.navigation.PoemRoute
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun PoemPlayerContent(
    modifier: Modifier = Modifier
) {
    val poemPlayerViewModel: PoemPlayerViewModel = koinViewModel()
    val state = poemPlayerViewModel.state.collectAsState().value
    val navigation = LocalNavController.current
    PoemPlayerBar(
        state = state,
        onCloseClick = {
            poemPlayerViewModel.closeClicked()
        },
        onPlayClick = {
            poemPlayerViewModel.playClicked()
        },
        onPauseClick = {
            poemPlayerViewModel.pauseClicked()
        },
        modifier = modifier.clickable {
            poemPlayerViewModel.poemAudio?.let {
                val route = PoemRoute(it.poemInfo.id)
                navigation.navigate(route)
            }
        }
    )
    KeepScreenOn(state?.state == MediaPlayerUiModel.State.Playing)
}