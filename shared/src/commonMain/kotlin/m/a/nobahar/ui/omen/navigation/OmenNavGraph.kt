package m.a.nobahar.ui.omen.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import m.a.nobahar.ui.LocalNavController
import m.a.nobahar.ui.omen.screen.OmenScreen
import m.a.nobahar.ui.omen.screen.OmenViewModel
import m.a.nobahar.ui.poem.navigation.PoemRoute
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.omenGraph() {
    composable<OmenRoute> {
        val viewModel: OmenViewModel = koinViewModel()
        val state = viewModel.state.collectAsStateWithLifecycle().value
        val navigation = LocalNavController.current
        LaunchedEffect(state) {
            state.data?.let {
                navigation.navigate(PoemRoute(it.poemId))
                viewModel.navigatedToPoemScreen()
            }
        }
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            OmenScreen(
                state = state,
                onRetryClick = {
                    viewModel.retryClicked()
                },
                onErrorDismiss = {
                    viewModel.errorDismissed()
                },
                onOmenClick = {
                    viewModel.omenClicked()
                },
            )
        }
    }
}