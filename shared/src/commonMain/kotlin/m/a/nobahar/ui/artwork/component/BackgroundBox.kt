package m.a.nobahar.ui.artwork.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import m.a.nobahar.ui.shared.ui.NobaharPreview
import m.a.nobahar.ui.theme.PoemThemePreview
import nobahar.shared.generated.resources.Res
import nobahar.shared.generated.resources.bg_poem_1
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun BackgroundBox(
    image: DrawableResource,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(86.dp)
            .clip(RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@NobaharPreview
@Composable
private fun BackgroundBoxPreview() {
    PoemThemePreview {
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            BackgroundBox(
                image = Res.drawable.bg_poem_1,
            )
            BackgroundBox(
                image = Res.drawable.bg_poem_1,
            )
        }
    }
}