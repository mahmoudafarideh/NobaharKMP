package m.a.nobahar.ui.book.navigation

import kotlinx.serialization.Serializable
import m.a.nobahar.analytics.AppMetricaAgent
import m.a.nobahar.analytics.PoetBookScreenEvent
import m.a.nobahar.domain.model.Poet

@Serializable
data class BookRoute(
    val id: Long,
    val name: String,
    val nickName: String,
    val profile: String,
    val bookId: Long,
    val bookName: String,
) {

    val poetInfo: Poet
        get() = Poet(
            id,
            name,
            nickName,
            profile,
        )

    init {
        AppMetricaAgent.log(PoetBookScreenEvent(poetInfo.id, bookId, poetInfo.nickName, bookName))
    }

}