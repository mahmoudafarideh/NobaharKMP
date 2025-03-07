package m.a.nobahar.ui.artwork.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import m.a.nobahar.ui.artwork.model.ArtScreenUiModel
import m.a.nobahar.ui.capture.CaptureController
import m.a.nobahar.ui.capture.capturable
import nobahar.shared.generated.resources.Res
import nobahar.shared.generated.resources.iran_nastaliq
import nobahar.shared.generated.resources.ordibehesht
import nobahar.shared.generated.resources.vazir_regular
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

val ordibeheshtFont @Composable get() = FontFamily(Font(Res.font.ordibehesht))
val nastaliqFont @Composable get() = FontFamily(Font(Res.font.iran_nastaliq))
val vazirFont @Composable get() = FontFamily(Font(Res.font.vazir_regular))

@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun ArtworkBox(
    firstVerse: String,
    secondVerse: String,
    poetName: String,
    state: ArtScreenUiModel,
    captureController: CaptureController,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.capturable(captureController)
    ) {
        Image(
            painter = painterResource(state.selectedBackground.image),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = firstVerse,
                style = MaterialTheme.typography.bodyMedium,
                color = state.selectedColor.color,
                fontSize = state.selectedFontSize.size.verseSize,
                fontFamily = state.selectedFont.font.fontFamily
            )
            Spacer(modifier = Modifier.size(4.dp))
            Text(
                text = secondVerse,
                style = MaterialTheme.typography.bodyMedium,
                color = state.selectedColor.color,
                fontSize = state.selectedFontSize.size.verseSize,
                fontFamily = state.selectedFont.font.fontFamily
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(0.7f))
            Text(
                text = poetName,
                style = MaterialTheme.typography.bodyMedium,
                color = state.selectedColor.color,
                fontSize = state.selectedFontSize.size.poetSize,
                modifier = Modifier.weight(0.3f),
                fontFamily = state.selectedFont.font.fontFamily
            )
        }
    }
}