package m.a.nobahar.ui.artwork.navigation

import kotlinx.serialization.Serializable
import m.a.compilot.common.RouteNavigation
import m.a.nobahar.domain.model.PoemVerse

@Serializable
data class ArtworkRoute(
    val first: PoemVerse,
    val second: PoemVerse,
    val poetName: String,
    val poemBook: String
) {
    companion object
}

@Serializable
data class SomeScreenRoute(
    val id: Int,
    val title: String
) {
    companion object
}