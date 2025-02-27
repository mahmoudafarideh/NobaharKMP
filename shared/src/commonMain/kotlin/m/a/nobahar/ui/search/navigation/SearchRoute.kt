package m.a.nobahar.ui.search.navigation

import kotlinx.serialization.Serializable
import m.a.nobahar.analytics.AppMetricaAgent
import m.a.nobahar.analytics.SearchScreenEvent
import m.a.nobahar.domain.model.Poet

@Serializable
data class SearchRoute(
    val poetInfo: Poet?,
    val book: Book?,
) {
    init {
        AppMetricaAgent.log(
            SearchScreenEvent(poetInfo?.id, book?.id, poetInfo?.nickName, book?.name)
        )
    }

    @Serializable
    data class Book(
        val id: Long,
        val name: String,
    )

    companion object
}