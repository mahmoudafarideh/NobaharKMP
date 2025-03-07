package m.a.nobahar.ui.artwork.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import m.a.nobahar.ui.LocalNavController
import m.a.nobahar.ui.artwork.model.ArtSavingState
import m.a.nobahar.ui.artwork.model.ArtScreenUiModel
import nobahar.shared.generated.resources.Res
import nobahar.shared.generated.resources.save_artwork_button_label
import org.jetbrains.compose.resources.stringResource


@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun ArtworkAppBar(
    state: ArtScreenUiModel,
    onSaveButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val navController = LocalNavController.current
    TopAppBar(
        title = {},
        navigationIcon = {
            Icon(
                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                tint = MaterialTheme.colorScheme.onBackground,
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        navController.popBackStack()
                    }
                    .padding(12.dp)
            )
        },
        actions = {
            AnimatedVisibility(
                visible = state.savingState == ArtSavingState.None,
                enter = scaleIn(),
                exit = scaleOut()
            ) {
                Button(
                    onClick = onSaveButtonClick,
                    modifier = Modifier.padding(end = 12.dp)
                ) {
                    Text(
                        text = stringResource(Res.string.save_artwork_button_label),
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
            }
        },
        modifier = modifier
    )
}