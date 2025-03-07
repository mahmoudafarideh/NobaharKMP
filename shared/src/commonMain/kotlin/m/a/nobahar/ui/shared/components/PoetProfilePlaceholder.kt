package m.a.nobahar.ui.shared.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import com.valentinilk.shimmer.shimmer
import m.a.nobahar.ui.shared.model.PoetUiModel
import nobahar.shared.generated.resources.Res
import nobahar.shared.generated.resources.circle
import org.jetbrains.compose.resources.painterResource

@Composable
fun PoetProfilePlaceholder(
    poetUiModel: PoetUiModel,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(Res.drawable.circle),
        modifier = modifier
            .aspectRatio(.8f)
            .shimmer(),
        contentDescription = poetUiModel.name,
        contentScale = ContentScale.FillBounds,
        colorFilter = ColorFilter.tint(Color.Gray)
    )
}