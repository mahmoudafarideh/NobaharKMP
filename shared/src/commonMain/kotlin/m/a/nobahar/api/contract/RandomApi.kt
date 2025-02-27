package m.a.nobahar.api.contract

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import m.a.nobahar.api.model.RandomPoemDto

class RandomApi(private val client: HttpClient) {

    suspend fun getRandomPoem(poetId: Long? = null): RandomPoemDto = client
        .get(urlString = "v1/poem/random") {
            this.url { urlBuilder ->
                poetId?.let {
                    urlBuilder.parameters.append("poetId", it.toString())
                }
            }
        }
        .body()

}