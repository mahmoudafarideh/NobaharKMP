package m.a.nobahar.di

import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpRequestRetry
import io.ktor.client.plugins.compression.ContentEncoding
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.KotlinxSerializationConverter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import org.koin.dsl.module

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
            install(ContentEncoding)
            install(ContentNegotiation) {
                json(get<Json>())

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
        }
    }
}
