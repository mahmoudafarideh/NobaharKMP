package m.a.nobahar.api.contract

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import m.a.nobahar.api.model.PoemInfoDto

class PoemApi(private val client: HttpClient) {
    suspend fun getPoem(id: Long): PoemInfoDto = client.get(urlString = "v1/poem/$id").body()

    suspend fun loadRecitationAudioSync(link: String) = client.get(
        urlString = link
    ).bodyAsText()
}