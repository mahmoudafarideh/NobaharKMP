package m.a.nobahar.ui.poem.navigation

import kotlinx.serialization.Serializable
import m.a.nobahar.analytics.AppMetricaAgent
import m.a.nobahar.analytics.PoemScreenEvent

@Serializable
data class PoemRoute(val poemId: Long) {
    init {
        AppMetricaAgent.log(PoemScreenEvent(poemId))
    }

    companion object
}