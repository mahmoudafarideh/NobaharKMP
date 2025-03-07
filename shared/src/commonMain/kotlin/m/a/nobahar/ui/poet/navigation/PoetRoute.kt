package m.a.nobahar.ui.poet.navigation

import kotlinx.serialization.Serializable
import m.a.nobahar.analytics.AppMetricaAgent
import m.a.nobahar.analytics.PoetScreenEvent
import m.a.nobahar.domain.model.Poet

@Serializable
data class PoetRoute(
    val id: Long,
    val name: String,
    val nickName: String,
    val profile: String
) {
    val poetInfo: Poet
        get() = Poet(
            id,
            name,
            nickName,
            profile,
        )

    init {
        AppMetricaAgent.log(PoetScreenEvent(poetInfo.id, poetInfo.nickName))
    }

}