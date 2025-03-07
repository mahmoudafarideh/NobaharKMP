package m.a.nobahar.ui.artwork.model

import nobahar.shared.generated.resources.Res
import nobahar.shared.generated.resources.background_tab_label
import nobahar.shared.generated.resources.color_tab_label
import nobahar.shared.generated.resources.font_tab_label
import org.jetbrains.compose.resources.StringResource

data class ArtTabUiModel(
    val tab: Tab,
    val selected: Boolean,
) {
    enum class Tab(val title: StringResource) {
        Font(Res.string.font_tab_label),
        Background(Res.string.background_tab_label),
        Color(Res.string.color_tab_label),
    }
}