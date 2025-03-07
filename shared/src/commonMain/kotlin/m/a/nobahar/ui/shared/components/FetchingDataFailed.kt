package m.a.nobahar.ui.shared.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import m.a.nobahar.ui.shared.ui.NobaharPreview
import m.a.nobahar.ui.theme.PoemThemePreview
import nobahar.shared.generated.resources.Res
import nobahar.shared.generated.resources.error_occured_label
import nobahar.shared.generated.resources.retry_button_label
import org.jetbrains.compose.resources.stringResource

@Composable
fun FetchingDataFailed(
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.errorContainer)
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                tint = MaterialTheme.colorScheme.error,
                contentDescription = null,
                modifier = Modifier
                    .size(56.dp)
                    .padding(8.dp)
            )
        }
        Spacer(modifier = Modifier.size(24.dp))
        Text(
            text = stringResource(Res.string.error_occured_label),
            modifier = Modifier,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.size(24.dp))
        Button(
            onClick = onRetryClick,
        ) {
            Text(
                text = stringResource(Res.string.retry_button_label),
                modifier = Modifier,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@NobaharPreview
@Composable
fun FetchingDataFailedPreview() {
    PoemThemePreview {
        FetchingDataFailed(
            onRetryClick = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}