package m.a.nobahar.ui.artwork.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import nobahar.shared.generated.resources.Res
import nobahar.shared.generated.resources.iran_nastaliq
import nobahar.shared.generated.resources.nastaliq_font_label
import nobahar.shared.generated.resources.ordibehesht
import nobahar.shared.generated.resources.ordibehesht_font_label
import nobahar.shared.generated.resources.vazir_font_label
import nobahar.shared.generated.resources.vazir_regular
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.FontResource
import org.jetbrains.compose.resources.StringResource

data class ArtFontUiModel(
    val font: Font,
    val selected: Boolean,
) {
    enum class Font(
        val font: FontResource,
        val label: StringResource,
    ) {
        Nastaliq(Res.font.iran_nastaliq, Res.string.nastaliq_font_label),
        Vazir(Res.font.vazir_regular, Res.string.vazir_font_label),
        Ordibehesht(Res.font.ordibehesht, Res.string.ordibehesht_font_label);

        val fontFamily @Composable get()  = FontFamily(Font(font))
    }
}