package m.a.nobahar.ui.shared.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import io.kamel.core.Resource
import io.kamel.core.config.DefaultCacheSize
import io.kamel.core.config.DefaultHttpCacheSize
import io.kamel.core.config.KamelConfig
import io.kamel.core.config.fileFetcher
import io.kamel.core.config.fileUrlFetcher
import io.kamel.core.config.httpUrlFetcher
import io.kamel.core.config.stringMapper
import io.kamel.core.config.uriMapper
import io.kamel.core.config.urlMapper
import io.kamel.image.asyncPainterResource
import io.kamel.image.config.LocalKamelConfig
import io.kamel.image.config.animatedImageDecoder

@Composable
fun UrlImage(
    url: String,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    placeholder: (@Composable () -> Unit)? = null,
) {
    val kamelConfig = rememberKamelConfig()
    CompositionLocalProvider(LocalKamelConfig provides kamelConfig) {
        Box {
            when (val resource = asyncPainterResource(data = url)) {
                is Resource.Failure -> {
                    placeholder?.invoke()
                }

                is Resource.Loading -> {
                    placeholder?.invoke()
                }

                is Resource.Success -> {
                    Image(
                        painter = resource.value,
                        modifier = modifier,
                        contentDescription = contentDescription,
                    )
                }
            }

        }
    }

}

@Composable
private fun rememberKamelConfig() = remember {
    KamelConfig {
        imageBitmapCacheSize = DefaultCacheSize
        imageVectorCacheSize = DefaultCacheSize
        svgCacheSize = DefaultCacheSize
        animatedImageCacheSize = DefaultCacheSize
        stringMapper()
        urlMapper()
        uriMapper()
        fileFetcher()
        fileUrlFetcher()
        httpUrlFetcher {
            httpCache(DefaultHttpCacheSize)
        }
        animatedImageDecoder()
    }
}