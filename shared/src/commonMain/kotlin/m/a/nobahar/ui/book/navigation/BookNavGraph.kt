package m.a.nobahar.ui.book.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import m.a.nobahar.domain.model.Poet
import m.a.nobahar.ui.book.screen.BookScreen
import m.a.nobahar.ui.book.screen.BookViewModel
import m.a.nobahar.ui.toPoetUiModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parameterSetOf

fun NavGraphBuilder.bookGraph() {
    composable<BookRoute> {
        val arg: BookRoute = it.toRoute()
        val viewModel = bookViewModel(arg.poetInfo, arg.bookId)
        val state by viewModel.state.collectAsState()
        BookScreen(
            poetUiModel = state.poet,
            onRetryClick = {
                viewModel.retryClicked()
            },
            bookInfo = state.items,
            modifier = Modifier.fillMaxSize(),
            bookId = arg.bookId,
            bookName = arg.bookName
        )
    }
}

@Composable
private fun bookViewModel(poet: Poet, bookId: Long): BookViewModel {
    val poetUiModel = remember(poet) {
        poet.toPoetUiModel()
    }
    return koinViewModel {
        parameterSetOf(poetUiModel, bookId)
    }
}

