package m.a.nobahar.ui.home.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import m.a.nobahar.analytics.AppMetricaAgent
import m.a.nobahar.analytics.OmenScreenEvent
import m.a.nobahar.ui.LocalNavController
import m.a.nobahar.ui.home.screen.HomeScreen
import m.a.nobahar.ui.home.screen.HomeViewModel
import m.a.nobahar.ui.omen.navigation.OmenRoute
import m.a.nobahar.ui.poet.navigation.PoetRoute
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.homeGraph() {
    composable<HomeRoute> {
        val viewModel: HomeViewModel = koinViewModel()
        val state by viewModel.state.collectAsState()
        val navigation = LocalNavController.current
        HomeScreen(
            centuries = state,
            modifier = Modifier,
            onCenturyClick = {
                viewModel.centuryClicked(it)
            },
            onRetryClick = {
                viewModel.retryClicked()
            },
            onPoetClick = {
                navigation
                    .navigate(
                        PoetRoute(
                            id = it.id,
                            name = it.name,
                            nickName = it.nickname,
                            profile = it.profile,
                        )
                    )
            },
            onOmenClick = {
                AppMetricaAgent.log(OmenScreenEvent)
                navigation.navigate(OmenRoute)
            }
        )
    }
}