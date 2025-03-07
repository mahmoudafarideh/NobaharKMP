package m.a.nobahar.ui.artwork.model

import org.jetbrains.compose.resources.DrawableResource

data class ArtBackgroundUiModel(
    val image: DrawableResource,
    val selected: Boolean,
)