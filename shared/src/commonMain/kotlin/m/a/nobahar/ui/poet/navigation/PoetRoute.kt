package m.a.nobahar.ui.poet.navigation

import kotlinx.serialization.Serializable
import m.a.nobahar.analytics.AppMetricaAgent
import m.a.nobahar.analytics.PoetScreenEvent
import m.a.nobahar.domain.model.Poet

@Serializable
data class PoetRoute(
    val poetInfo: Poet
) {
    init {
        AppMetricaAgent.log(PoetScreenEvent(poetInfo.id, poetInfo.nickName))
    }

    companion object
}