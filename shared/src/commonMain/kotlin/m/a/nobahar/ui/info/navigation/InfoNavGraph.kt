package m.a.nobahar.ui.info.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.dialog
import m.a.nobahar.ui.info.screen.InfoDialog

fun NavGraphBuilder.infoGraph() {
    dialog<InfoRoute> {
        InfoDialog(
            modifier = Modifier,
        )
    }
}