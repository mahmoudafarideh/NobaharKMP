package m.a.nobahar.api.contract

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import m.a.nobahar.api.model.PoemInfoDto
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Streaming
import retrofit2.http.Url

class PoemApi(private val client: HttpClient) {
    suspend fun getPoem(id: Long): PoemInfoDto = client.get(urlString = "v1/poem/$id").body()

    @Streaming
    @GET
    suspend fun loadRecitationAudioSync(@Url link: String): ResponseBody
}