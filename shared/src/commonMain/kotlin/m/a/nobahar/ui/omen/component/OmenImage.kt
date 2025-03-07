package m.a.nobahar.ui.omen.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import m.a.nobahar.domain.model.LoadableData
import m.a.nobahar.domain.model.Loading
import m.a.nobahar.ui.omen.model.OmenUiModel
import nobahar.shared.generated.resources.Res
import nobahar.shared.generated.resources.omen_image
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun OmenImage(
    state: LoadableData<OmenUiModel>,
    onOmenClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition()
    val angle by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = if (state is Loading) 360f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(400, easing = LinearEasing)
        )
    )
    Image(
        painter = painterResource(Res.drawable.omen_image),
        contentDescription = null,
        modifier = modifier
            .then(
                if (state is Loading) {
                    Modifier.rotate(angle)
                } else {
                    Modifier
                }
            )
            .clip(CircleShape)
            .clickable {
                onOmenClick()
            }
    )
}