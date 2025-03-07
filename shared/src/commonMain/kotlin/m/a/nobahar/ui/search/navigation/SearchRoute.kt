package m.a.nobahar.ui.search.navigation

import kotlinx.serialization.Serializable
import m.a.nobahar.analytics.AppMetricaAgent
import m.a.nobahar.analytics.SearchScreenEvent
import m.a.nobahar.domain.model.Poet

@Serializable
data class SearchRoute(
    val poetId: Long? = null,
    val poetName: String? = null,
    val poetNickName: String? = null,
    val poetProfile: String? = null,
    val bookId: Long? = null,
    val bookName: String? = null,
) {

    val poetInfo: Poet?
        get() = poetId?.let {
            Poet(
                poetId,
                poetName!!,
                poetNickName!!,
                poetProfile!!,
            )
        }

    val book: Book?
        get() = bookId?.let {
            Book(
                bookId,
                bookName!!,
            )
        }

    init {
        AppMetricaAgent.log(
            SearchScreenEvent(poetInfo?.id, book?.id, poetInfo?.nickName, book?.name)
        )
    }

    data class Book(
        val id: Long,
        val name: String,
    )
}