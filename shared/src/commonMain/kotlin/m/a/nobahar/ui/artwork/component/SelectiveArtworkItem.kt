package m.a.nobahar.ui.artwork.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import m.a.nobahar.ui.shared.ui.NobaharPreview
import m.a.nobahar.ui.theme.PoemThemePreview
import nobahar.shared.generated.resources.Res
import nobahar.shared.generated.resources.bg_poem_1

@Composable
fun SelectiveArtworkItem(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .border(
                2.dp,
                when {
                    selected -> MaterialTheme.colorScheme.primary
                    else -> MaterialTheme.colorScheme.onSurfaceVariant
                },
                RoundedCornerShape(16.dp),
            )
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                onClick()
            }
            .padding(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
        RadioButton(
            selected = selected,
            onClick = null
        )
    }
}

@NobaharPreview
@Composable
private fun SelectiveArtworkItemPreview() {
    PoemThemePreview {
        Column {
            SelectiveArtworkItem(
                selected = false,
                onClick = {}
            ) {
                BackgroundBox(Res.drawable.bg_poem_1)
            }
            SelectiveArtworkItem(
                selected = true,
                onClick = {}
            ) {
                BackgroundBox(Res.drawable.bg_poem_1)
            }
        }
    }
}