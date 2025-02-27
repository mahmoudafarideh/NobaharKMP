package m.a.nobahar.ui.omen.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nobahar.shared.generated.resources.Res
import nobahar.shared.generated.resources.omen_final_step_label
import nobahar.shared.generated.resources.omen_first_step_label
import nobahar.shared.generated.resources.omen_sentence_label
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun OmenSteps(
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(Res.string.omen_first_step_label),
            style = MaterialTheme.typography.bodySmall,
            color = Color.White
        )
        Spacer(modifier = Modifier.size(12.dp))
        Text(
            text = stringResource(Res.string.omen_sentence_label),
            style = MaterialTheme.typography.bodySmall,
            color = Color.White
        )
        Spacer(modifier = Modifier.size(24.dp))
        Text(
            text = stringResource(Res.string.omen_final_step_label),
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,
            lineHeight = 26.sp,
        )
    }
}