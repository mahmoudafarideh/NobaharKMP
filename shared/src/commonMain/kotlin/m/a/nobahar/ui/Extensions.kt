package m.a.nobahar.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import m.a.nobahar.domain.model.Poet
import m.a.nobahar.ui.search.model.SearchBookUiModel
import m.a.nobahar.ui.search.navigation.SearchRoute
import m.a.nobahar.ui.shared.model.PoetUiModel

fun Poet.toPoetUiModel() = PoetUiModel(
    name = name,
    nickname = nickName,
    profile = profile,
    id = id
)

fun SearchRoute.Book.toSearchBookUiModel(): SearchBookUiModel =
    SearchBookUiModel(id, name)

@Composable
fun Modifier.noRippleClickable(
    onClick: () -> Unit
) = then(
    Modifier.clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null
    ) {
        onClick()
    }
)

val LocalSnackBarHostState =
    compositionLocalOf<SnackbarHostState> { error("No SnackbarHostState found!") }

val LocalNavController = compositionLocalOf<NavController> { error("No SnackbarHostState found!") }

@Composable
expect fun KeepScreenOn(state: Boolean)

@Composable
expect fun BackHandler(enabled: Boolean, onBackClick: () -> Unit)

@Composable
expect fun goToMarket(): () -> Unit

@Composable
expect fun goToUrl(url: String): () -> Unit

@Composable
expect fun goToMatnnegarMarket(): () -> Unit

@Composable
expect fun goToInstagram(): () -> Unit

@Composable
expect fun goToTelegram(): () -> Unit

expect fun logInfo(key: String, data: Any?)
