package m.a.nobahar.ui.shared.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.LocalPlatformContext
import coil3.compose.rememberAsyncImagePainter
import coil3.network.NetworkClient
import coil3.network.NetworkFetcher
import coil3.network.ktor2.KtorNetworkFetcherFactory
import coil3.request.ImageRequest
import m.a.nobahar.ui.logInfo

@OptIn(ExperimentalCoilApi::class)
@Composable
fun UrlImage(
    url: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    placeholder: (@Composable () -> Unit)? = null,
) {
    Box {
        var isImageLoaded by remember { mutableStateOf(false) }
        val ctx = LocalPlatformContext.current
        val painter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(ctx)
                .data(url)
                .fetcherFactory(
                    KtorNetworkFetcherFactory()
                )
                .build(),
            onSuccess = {
                logInfo("SXO", "IMAGE LOADED")
                isImageLoaded = true
            },
            onError = {
                logInfo("SXO", it.result.throwable.message)
            }
        )
        if (!isImageLoaded) {
            placeholder?.invoke()
        }
        Image(
            painter = painter,
            modifier = modifier,
            contentDescription = contentDescription,
        )
    }
}