package m.a.nobahar.di

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asSkiaBitmap
import kotlinx.browser.document
import m.a.nobahar.api.helper.GetAppInfo
import m.a.nobahar.repository.GetAppInfoImp
import m.a.nobahar.storage.ImageBitmapSaver
import org.jetbrains.skia.Image
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.w3c.dom.HTMLAnchorElement
import org.w3c.dom.url.URL
import org.w3c.files.Blob
import org.w3c.files.BlobPropertyBag

actual fun Module.imageSaverModule(): KoinDefinition<ImageBitmapSaver> {
    return single<ImageBitmapSaver> {
        object : ImageBitmapSaver {
            override fun savePhoto(bitmap: ImageBitmap) {
                val image = Image.makeFromBitmap(bitmap.asSkiaBitmap())
                val pngData = image.encodeToData()!!.bytes // Convert to PNG bytes

                val blob = Blob(arrayOf(pngData), BlobPropertyBag(type = "image/png"))

                val url = URL.createObjectURL(blob)

                val link = document.createElement("a") as HTMLAnchorElement
                link.href = url
                link.download = "Nobahar.png"
                document.body?.appendChild(link)

                link.click()

                document.body?.removeChild(link)
                URL.revokeObjectURL(url)
            }
        }
    }
}

actual fun Module.appInfoModule(): KoinDefinition<GetAppInfo> {
    return single<GetAppInfo> {
        GetAppInfoImp()
    }
}