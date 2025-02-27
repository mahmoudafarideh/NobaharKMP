package m.a.nobahar.api.contract

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import m.a.nobahar.api.model.SearchResultDto

class SearchApi(private val client: HttpClient) {
    suspend fun searchPoem(
        page: Int, limit: Int, term: String, poetId: Long?, bookId: Long?,
    ): List<SearchResultDto> = client
        .get(urlString = "v1/poem/search") {
            url {
                it.parameters.apply {
                    append("page", page.toString())
                    append("limit", limit.toString())
                    append("term", term)
                    poetId?.let {
                        append("poetId", poetId.toString())
                    }
                    bookId?.let {
                        append("bookId", bookId.toString())
                    }
                }
            }
        }
        .body()

}