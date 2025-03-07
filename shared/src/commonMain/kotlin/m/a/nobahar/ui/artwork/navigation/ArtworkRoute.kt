package m.a.nobahar.ui.artwork.navigation

import kotlinx.serialization.Serializable
import m.a.nobahar.domain.model.PoemVerse

@Serializable
data class ArtworkRoute(
    val firstVerse: String,
    val secondVerse: String,
    val poetName: String,
    val poemBook: String
) {
    val first: PoemVerse get() = PoemVerse(
        firstVerse, 0, 0
    )
    val second: PoemVerse get() = PoemVerse(
        secondVerse, 0, 0
    )
}