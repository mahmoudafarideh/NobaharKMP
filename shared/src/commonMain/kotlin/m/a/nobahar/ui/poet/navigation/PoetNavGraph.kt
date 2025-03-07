package m.a.nobahar.ui.poet.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import m.a.nobahar.domain.model.Loading
import m.a.nobahar.domain.model.Poet
import m.a.nobahar.ui.LocalNavController
import m.a.nobahar.ui.poem.navigation.PoemRoute
import m.a.nobahar.ui.poet.screen.PoetScreen
import m.a.nobahar.ui.poet.screen.PoetViewModel
import m.a.nobahar.ui.toPoetUiModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

fun NavGraphBuilder.poetGraph() {
    composable<PoetRoute> {
        val viewModel = poetViewModel(it.toRoute<PoetRoute>().poetInfo)
        val state by viewModel.state.collectAsState()
        ObserveRandomPoem(viewModel)
        PoetScreen(
            poetUiModel = state.poet,
            selectedTab = state.selectedTabsUiModel,
            onTabClick = { tabsUiModel ->
                viewModel.tabClicked(tabsUiModel)
            },
            poetInfo = state.poetInfo,
            onRetryClick = {
                viewModel.retryClicked()
            },
            loadingRandomPoem = state.randomPoem is Loading,
            onRandomPoemClick = {
                viewModel.randomPoemClicked()
            }
        )
    }
}

@Composable
private fun ObserveRandomPoem(viewModel: PoetViewModel) {
    val navigation = LocalNavController.current
    LaunchedEffect(Unit) {
        viewModel.randomPoemFLow.collect {
            navigation.navigate(PoemRoute(it))
        }
    }
}

@Composable
private fun poetViewModel(poet: Poet): PoetViewModel {
    val poetUiModel = remember(poet) {
        poet.toPoetUiModel()
    }
    return koinViewModel {
        parametersOf(poetUiModel)
    }
}
