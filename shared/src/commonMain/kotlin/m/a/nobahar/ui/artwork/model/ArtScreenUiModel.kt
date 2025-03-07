package m.a.nobahar.ui.artwork.model

import androidx.compose.ui.graphics.Color
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import nobahar.shared.generated.resources.Res
import nobahar.shared.generated.resources.bg_poem_1
import nobahar.shared.generated.resources.bg_poem_10
import nobahar.shared.generated.resources.bg_poem_2
import nobahar.shared.generated.resources.bg_poem_3
import nobahar.shared.generated.resources.bg_poem_4
import nobahar.shared.generated.resources.bg_poem_5
import nobahar.shared.generated.resources.bg_poem_6
import nobahar.shared.generated.resources.bg_poem_7
import nobahar.shared.generated.resources.bg_poem_8
import nobahar.shared.generated.resources.bg_poem_9

data class ArtScreenUiModel(
    val tabs: ImmutableList<ArtTabUiModel>,
    val fonts: ImmutableList<ArtFontUiModel>,
    val backgrounds: ImmutableList<ArtBackgroundUiModel>,
    val colors: ImmutableList<ArtColorUiModel>,
    val fontSizes: ImmutableList<ArtFontSizeUiModel>,
    val savingState: ArtSavingState,
) {
    val selectedFont = fonts.first { it.selected }
    val selectedFontSize = fontSizes.first { it.selected }
    val selectedBackground = backgrounds.first { it.selected }
    val selectedColor = colors.first { it.selected }
    val selectedTab = tabs.first { it.selected }

    companion object {
        val default = ArtScreenUiModel(
            tabs = ArtTabUiModel.Tab.entries.mapIndexed { index, item ->
                ArtTabUiModel(item, index == 0)
            }.toImmutableList(),
            fonts = ArtFontUiModel.Font.entries.mapIndexed { index, item ->
                ArtFontUiModel(item, index == 0)
            }.toImmutableList(),
            backgrounds = persistentListOf(
                ArtBackgroundUiModel(Res.drawable.bg_poem_1, true),
                ArtBackgroundUiModel(Res.drawable.bg_poem_2, false),
                ArtBackgroundUiModel(Res.drawable.bg_poem_3, false),
                ArtBackgroundUiModel(Res.drawable.bg_poem_4, false),
                ArtBackgroundUiModel(Res.drawable.bg_poem_5, false),
                ArtBackgroundUiModel(Res.drawable.bg_poem_6, false),
                ArtBackgroundUiModel(Res.drawable.bg_poem_7, false),
                ArtBackgroundUiModel(Res.drawable.bg_poem_8, false),
                ArtBackgroundUiModel(Res.drawable.bg_poem_9, false),
                ArtBackgroundUiModel(Res.drawable.bg_poem_10, false),
            ),
            colors = persistentListOf(
                ArtColorUiModel(Color.White, true),
                ArtColorUiModel(Color.Black, false),
                ArtColorUiModel(Color.Yellow, false),
                ArtColorUiModel(Color.Red, false),
                ArtColorUiModel(Color.Green, false),
                ArtColorUiModel(Color.Cyan, false),
                ArtColorUiModel(Color.Magenta, false),
                ArtColorUiModel(Color.LightGray, false),
            ),
            fontSizes = ArtFontSizeUiModel.Size.entries.mapIndexed { index, item ->
                ArtFontSizeUiModel(item, index == 2)
            }.toImmutableList(),
            savingState = ArtSavingState.None
        )
    }
}