package m.a.nobahar.api.contract

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import m.a.nobahar.api.model.BookDetailDto

class BookApi(private val client: HttpClient) {
    suspend fun getBook(id: Long): BookDetailDto = client.get(
        urlString = "v1/book/$id"
    ).body()
}