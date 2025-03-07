package m.a.nobahar.ui.poem.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import m.a.nobahar.ui.LocalNavController
import m.a.nobahar.ui.poem.screen.PoemScreen
import m.a.nobahar.ui.poem.screen.PoemViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.poemGraph() {
    composable<PoemRoute> {
        val viewModel = poemViewModel(it.toRoute<PoemRoute>().poemId)
        val state by viewModel.state.collectAsStateWithLifecycle()
        val navigation = LocalNavController.current
        PoemScreen(
            onRetryClick = {
                viewModel.retryClicked()
            },
            poemUiModel = state.poem,
            onPoemClick = { id ->
                navigation.navigate(PoemRoute(id))
            },
            modifier = Modifier.fillMaxSize(),
            onRecitationClicked = { recitation ->
                viewModel.recitationClicked(recitation)
            },
            onVerseClick = { verse ->
                viewModel.verseClicked(verse)
            },
            onVersesCopyClick = {
                viewModel.releaseVerses()
            },
            onVerseArtworkClick = {
                viewModel.releaseVerses()
            }
        )
    }
}

@Composable
private fun poemViewModel(poemId: Long): PoemViewModel {
    return koinViewModel {
        parametersOf(poemId)
    }
}
