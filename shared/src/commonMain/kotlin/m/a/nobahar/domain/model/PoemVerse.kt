package m.a.nobahar.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class PoemVerse(
    val text: String,
    val id: Long,
    val couple: Int
)