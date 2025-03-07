package m.a.nobahar.ui.splash.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import m.a.nobahar.domain.model.Loaded
import m.a.nobahar.ui.LocalNavController
import m.a.nobahar.ui.home.navigation.HomeRoute
import m.a.nobahar.ui.splash.screen.SplashScreen
import m.a.nobahar.ui.splash.screen.SplashViewModel
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.splashGraph() {
    composable<SplashRoute> {
        val viewModel: SplashViewModel = koinViewModel()
        val state = viewModel.state.collectAsState().value
        val navigation = LocalNavController.current
        LaunchedEffect(state) {
            if (state is Loaded) {
                navigation.navigate(HomeRoute)
            }
        }
        SplashScreen(
            state = state,
            modifier = Modifier,
            onRetryClick = {
                viewModel.retryClicked()
            }
        )
    }
}