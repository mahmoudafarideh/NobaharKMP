package m.a.nobahar.api.contract

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import m.a.nobahar.api.model.PoetDetailsDto

class PoetApi(private val client: HttpClient) {
    suspend fun getPoetDetails(id: Long): PoetDetailsDto =
        client.get(urlString = "v1/poet/${id}").body()
}