package m.a.nobahar.ui.omen.component

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import m.a.nobahar.domain.model.LoadableData
import m.a.nobahar.ui.omen.model.OmenUiModel
import nobahar.shared.generated.resources.Res
import nobahar.shared.generated.resources.omen_footer
import nobahar.shared.generated.resources.omen_header
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun OmenVerticalContent(
    shouldShowMessageBar: Boolean,
    state: LoadableData<OmenUiModel>,
    onOmenClick: () -> Unit
) {
    val boxWeight by animateFloatAsState(
        if (shouldShowMessageBar) 1f else .01f,
        tween(1_200)
    )
    Column(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .fillMaxHeight()
            .width(IntrinsicSize.Min),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(Res.drawable.omen_header),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        Box(
            modifier = Modifier
                .weight(boxWeight)
                .animateContentSize(tween(1_200))
                .background(Color(34, 25, 125))
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OmenSteps(modifier = Modifier)
                Spacer(modifier = Modifier.size(12.dp))
                OmenImage(state, onOmenClick, Modifier.size(144.dp))
            }
        }
        Image(
            painter = painterResource(Res.drawable.omen_footer),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth()
        )
        ((1f.minus(boxWeight)).div(2)).takeIf {
            it > 0f
        }?.let {
            Spacer(modifier = Modifier.weight(it))
        }
    }
}
