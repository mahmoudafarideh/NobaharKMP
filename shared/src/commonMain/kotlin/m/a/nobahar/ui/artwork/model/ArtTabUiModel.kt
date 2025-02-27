package m.a.nobahar.ui.artwork.model

import androidx.annotation.StringRes
import m.a.nobahar.R

data class ArtTabUiModel(
    val tab: Tab,
    val selected: Boolean,
) {
    enum class Tab(@StringRes val title: Int) {
        Font(Res.string.font_tab_label),
        Background(Res.string.background_tab_label),
        Color(Res.string.color_tab_label),
    }
}