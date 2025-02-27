package m.a.nobahar.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Poet(
    val id: Long,
    val name: String,
    val nickName: String,
    val profile: String
)