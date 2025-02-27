package m.a.nobahar.ui.info.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import m.a.nobahar.ui.LocalNavController
import m.a.nobahar.ui.home.component.GanjoorBar
import m.a.nobahar.ui.noRippleClickable
import m.a.nobahar.ui.shared.ui.NobaharPreview
import m.a.nobahar.ui.splash.screen.NobaharSlogan
import m.a.nobahar.ui.theme.PoemThemePreview
import nobahar.shared.generated.resources.Res
import nobahar.shared.generated.resources.ic_instagram
import nobahar.shared.generated.resources.ic_telegram
import org.jetbrains.compose.resources.painterResource

@Composable
fun InfoDialog(modifier: Modifier = Modifier) {
    val navigation = LocalNavController.current
    Box(
        modifier = modifier
            .fillMaxSize()
            .noRippleClickable {
                navigation.popBackStack()
            },
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .noRippleClickable {}
                .clip(RoundedCornerShape(24.dp))
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NobaharSlogan()
            Spacer(Modifier.size(24.dp))
            GanjoorBar()
            Spacer(modifier = Modifier.height(12.dp))
            Row {
                Icon(
                    painter = painterResource(Res.drawable.ic_instagram),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null,
                    modifier = modifier
                        .clip(CircleShape)
                        .clickable {
                        }
                        .padding(12.dp)
                )
                Icon(
                    painter = painterResource(Res.drawable.ic_telegram),
                    tint = MaterialTheme.colorScheme.primary,
                    contentDescription = null,
                    modifier = modifier
                        .clip(CircleShape)
                        .clickable {

                        }
                        .padding(12.dp)
                )
            }
        }
    }
}


@NobaharPreview
@Composable
private fun InfoDialogPreview() {
    PoemThemePreview {
        InfoDialog()
    }
}