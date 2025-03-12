package m.a.nobahar.di

import io.ktor.client.HttpClient
import io.ktor.client.call.HttpClientCall
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.compression.ContentEncoding
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.statement.HttpReceivePipeline
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpProtocolVersion
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import io.ktor.serialization.kotlinx.json.json
import io.ktor.serialization.kotlinx.xml.xml
import io.ktor.util.date.GMTDate
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.InternalAPI
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext

const val WEB_URL = "https://api.ganjoor.net"
const val API_URL = "https://nobahar.app/api/"


@OptIn(ExperimentalSerializationApi::class)
val networkModules = module {
    single {
        Json {
            this.ignoreUnknownKeys = true
            this.allowComments = true
            this.explicitNulls = false
            this.coerceInputValues = true
        }
    }

    single<HttpClient> {
        HttpClient {
            install(ContentEncoding) {
                gzip()
            }
            install(ContentNegotiation) {
                json(get<Json>())
                xml()
                register(
                    ContentType.Text.Html, KotlinxSerializationConverter(get<Json>())
                )
                register(
                    ContentType.Text.Plain, KotlinxSerializationConverter(get<Json>())
                )
            }

            install(DefaultRequest) {
                url(API_URL)
                headers.append("Accept", "application/json")
            }
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.HEADERS
            }
            install(HttpRequestRetry) {
                maxRetries = 1
                retryOnServerErrors(maxRetries)
                exponentialDelay()
            }
        }.apply {

            receivePipeline.intercept(HttpReceivePipeline.Before) {
                val newHeaders = Headers.build {
                    appendAll(it.headers) // Copy original headers
                    remove("Content-Length")    // Remove Content-Length
                }
                this.proceedWith(ContentLenLessHttpResponse(it, newHeaders))
            }

        }
    }
}

private class ContentLenLessHttpResponse(
    httpResponse: HttpResponse,
    newHeaders: Headers
) : HttpResponse() {
    override val call: HttpClientCall = httpResponse.call
    override val coroutineContext: CoroutineContext = httpResponse.coroutineContext
    override val headers: Headers = newHeaders

    @InternalAPI
    override val rawContent: ByteReadChannel = httpResponse.rawContent
    override val requestTime: GMTDate = httpResponse.requestTime
    override val responseTime: GMTDate = httpResponse.responseTime
    override val status: HttpStatusCode = httpResponse.status
    override val version: HttpProtocolVersion = httpResponse.version

}
