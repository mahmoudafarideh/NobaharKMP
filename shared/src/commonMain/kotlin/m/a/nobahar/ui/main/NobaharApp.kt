package m.a.nobahar.ui.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController.OnDestinationChangedListener
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import m.a.nobahar.di.koinModules
import m.a.nobahar.ui.LocalNavController
import m.a.nobahar.ui.LocalSnackBarHostState
import m.a.nobahar.ui.artwork.navigation.artworkGraph
import m.a.nobahar.ui.book.navigation.bookGraph
import m.a.nobahar.ui.home.navigation.homeGraph
import m.a.nobahar.ui.info.navigation.infoGraph
import m.a.nobahar.ui.main.component.PoemPlayerContent
import m.a.nobahar.ui.omen.navigation.omenGraph
import m.a.nobahar.ui.poem.navigation.poemGraph
import m.a.nobahar.ui.poet.navigation.poetGraph
import m.a.nobahar.ui.search.navigation.searchGraph
import m.a.nobahar.ui.splash.navigation.SplashRoute
import m.a.nobahar.ui.splash.navigation.splashGraph
import m.a.nobahar.ui.theme.PoemTheme
import org.koin.compose.KoinApplication

@Composable
fun MainContent() {
    KoinApplication(application = { modules(koinModules) }) {
        PoemTheme {
            val navigation = rememberNavController()
            val snackBarHostState = remember { SnackbarHostState() }
            CompositionLocalProvider(LocalNavController provides navigation) {
                CompositionLocalProvider(LocalSnackBarHostState provides snackBarHostState) {
                    MainContent(snackBarHostState, navigation)
                }
            }
        }
    }
}

@Composable
internal fun MainContent(
    snackBarHostState: SnackbarHostState,
    navigation: NavHostController,
    modifier: Modifier = Modifier
) {
    var splashPassed by remember {
        mutableStateOf(false)
    }
    CheckSplashPassed(navigation) {
        splashPassed = true
    }
    Box(modifier) {
        Column(modifier = Modifier) {
            PoemPlayerContent()
            NavHost(
                navController = navigation,
                startDestination = SplashRoute,
                enterTransition = { EnterTransition.Companion.None },
                exitTransition = { ExitTransition.Companion.None },
            ) {
                this.homeGraph()
                this.poetGraph()
                this.bookGraph()
                this.poemGraph()
                this.searchGraph()
                this.omenGraph()
                this.infoGraph()
                this.artworkGraph()
                this.splashGraph()
            }
        }
        val navController = LocalNavController.current
        LaunchedEffect(splashPassed) {
//            if (splashPassed) {
//                navigationFlow.filterNotNull().collect {
//                    navController.navigate(it)
//                    navigationFlow.update { null }
//                }
//            }
        }
        SnackbarHost(
            hostState = snackBarHostState,
            modifier = Modifier.padding(top = 24.dp)
        )
    }
}

@Composable
private fun CheckSplashPassed(
    navigation: NavHostController,
    splashPassed: () -> Unit
) {
    DisposableEffect(Unit) {
        val listener =
            OnDestinationChangedListener { controller, destination, arguments ->
//                if (destination.route != SplashRoute) {
//                    splashPassed()
//                }
            }
        navigation.addOnDestinationChangedListener(listener)
        onDispose {
            navigation.removeOnDestinationChangedListener(listener)
        }
    }
}
