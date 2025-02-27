package m.a.nobahar.api.contract

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.headers
import m.a.nobahar.api.model.SplashDto

class SplashApi(private val client: HttpClient) {
    suspend fun getSplash(
        firebaseToken: String?,
        appVersion: Long,
        deviceId: Long?,
    ): SplashDto = client.get(urlString = "v1/splash") {
        headers {
            append("appVersion", appVersion.toString())
            append("deviceId", deviceId.toString())
            append("firebaseToken", firebaseToken.toString())
        }
    }.body()
}