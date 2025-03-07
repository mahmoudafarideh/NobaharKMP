package m.a.nobahar.ui.artwork.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nobahar.shared.generated.resources.Res
import nobahar.shared.generated.resources.matnnegar
import nobahar.shared.generated.resources.powered_by_matnnegar
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun MatnnegarRow(
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(Res.drawable.matnnegar),
            modifier = Modifier.size(32.dp),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(12.dp))
        Text(
            text = stringResource(Res.string.powered_by_matnnegar),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}