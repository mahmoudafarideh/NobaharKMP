package m.a.nobahar.ui.omen.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import m.a.nobahar.domain.model.LoadableData
import m.a.nobahar.domain.model.Loading
import m.a.nobahar.ui.LocalNavController
import m.a.nobahar.ui.omen.component.ErrorMessageSnack
import m.a.nobahar.ui.omen.component.OmenHorizontalContent
import m.a.nobahar.ui.omen.component.OmenVerticalContent
import m.a.nobahar.ui.omen.model.OmenUiModel
import m.a.nobahar.ui.shared.ui.LocalWindowSize
import m.a.nobahar.ui.shared.ui.NobaharPreview
import m.a.nobahar.ui.theme.PoemThemePreview

@Composable
fun OmenScreen(
    state: LoadableData<OmenUiModel>,
    onErrorDismiss: () -> Unit,
    onRetryClick: () -> Unit,
    onOmenClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val windowSize = LocalWindowSize.current

    var shouldShowMessageBar by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(100)
        shouldShowMessageBar = true
    }

    val navController = LocalNavController.current

    ErrorMessageSnack(state, onRetryClick, onErrorDismiss)

    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Icon(
            imageVector = Icons.AutoMirrored.Default.ArrowBack,
            tint = MaterialTheme.colorScheme.onBackground,
            contentDescription = null,
            modifier = Modifier
                .padding(top = 8.dp, start = 4.dp)
                .align(Alignment.TopStart)
                .clip(CircleShape)
                .clickable {
                    navController.popBackStack()
                }
                .padding(12.dp)
        )

        if (windowSize.widthSizeClass == WindowWidthSizeClass.Compact) {
            OmenVerticalContent(
                shouldShowMessageBar,
                state,
                onOmenClick
            )
        } else {
            OmenHorizontalContent(
                shouldShowMessageBar,
                state,
                onOmenClick
            )
        }
    }


}

@NobaharPreview
@Composable
fun OmenScreenPreview() {
    PoemThemePreview {
        OmenScreen(
            state = Loading,
            modifier = Modifier.fillMaxSize(),
            onErrorDismiss = {},
            onRetryClick = {},
            onOmenClick = {},
        )
    }
}