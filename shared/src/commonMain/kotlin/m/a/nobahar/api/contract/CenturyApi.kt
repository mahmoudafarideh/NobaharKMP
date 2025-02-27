package m.a.nobahar.api.contract

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import m.a.nobahar.api.model.CenturyPoetsDto

class CenturyApi(private val client: HttpClient) {
    suspend fun getCenturies(): List<CenturyPoetsDto> =
        client.get(urlString = "v1/poet/centuries").body()
}