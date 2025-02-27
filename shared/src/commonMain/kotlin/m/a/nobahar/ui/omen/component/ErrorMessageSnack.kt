package m.a.nobahar.ui.omen.component

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import m.a.nobahar.domain.model.Failed
import m.a.nobahar.domain.model.LoadableData
import m.a.nobahar.ui.LocalSnackBarHostState
import m.a.nobahar.ui.omen.model.OmenUiModel
import nobahar.shared.generated.resources.Res
import nobahar.shared.generated.resources.error_occured_label
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun ErrorMessageSnack(
    state: LoadableData<OmenUiModel>,
    onRetryClick: () -> Unit,
    onErrorDismiss: () -> Unit
) {
    val snackbarHostState = LocalSnackBarHostState.current
    val message = stringResource(Res.string.error_occured_label)
    LaunchedEffect(state) {
        if (state is Failed) {
            val result = snackbarHostState.showSnackbar(
                message = message,
                withDismissAction = false,
                duration = SnackbarDuration.Short
            )
            when (result) {
                SnackbarResult.ActionPerformed -> {
                    onRetryClick()
                }

                SnackbarResult.Dismissed -> {
                    onErrorDismiss()
                }
            }
        }
    }
}