package m.a.nobahar.ui.search.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import m.a.nobahar.domain.model.Poet
import m.a.nobahar.ui.LocalNavController
import m.a.nobahar.ui.poem.navigation.PoemRoute
import m.a.nobahar.ui.search.screen.SearchScreen
import m.a.nobahar.ui.search.screen.SearchViewModel
import m.a.nobahar.ui.toPoetUiModel
import m.a.nobahar.ui.toSearchBookUiModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.searchGraph() {
    composable<SearchRoute> {
        val arg = it.toRoute<SearchRoute>()
        val viewModel = searchViewModel(arg.poetInfo, arg.book)
        val navigation = LocalNavController.current
        val state by viewModel.state.collectAsState()
        SearchScreen(
            state = state,
            onTermChange = { s -> viewModel.termChanged(s) },
            onBackClick = { navigation.popBackStack() },
            onPoemClick = { _, poemId ->
                navigation.navigate(PoemRoute(poemId = poemId))
            },
            onRetryClick = {
                viewModel.retryClicked()
            },
            onListReachEnd = {
                viewModel.listReachedEnd()
            }
        )
    }
}

@Composable
private fun searchViewModel(
    poet: Poet?,
    book: SearchRoute.Book?
): SearchViewModel {
    val poetUiModel = remember(poet) {
        poet?.toPoetUiModel()
    }
    val bookUiModel = remember(book) {
        book?.toSearchBookUiModel()
    }
    return koinViewModel {
        parametersOf(poetUiModel, bookUiModel)
    }
}
