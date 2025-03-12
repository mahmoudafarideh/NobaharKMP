package m.a.nobahar.di

import androidx.compose.ui.graphics.ImageBitmap
import m.a.nobahar.api.helper.GetAppInfo
import m.a.nobahar.repository.GetAppInfoImp
import m.a.nobahar.storage.ImageBitmapSaver
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module

actual fun Module.imageSaverModule(): KoinDefinition<ImageBitmapSaver> {
    return single<ImageBitmapSaver> {
        object : ImageBitmapSaver {
            override fun savePhoto(bitmap: ImageBitmap) {
//                val paths = NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, true)
//                val filePath = paths.first() as String + "/$fileName"
//                val fileURL = NSURL.fileURLWithPath(filePath)
//
//                val imageData = UIImagePNGRepresentation(image)
//                imageData?.writeToURL(fileURL, true)
            }
        }
    }
}

actual fun Module.appInfoModule(): KoinDefinition<GetAppInfo> {
    return single<GetAppInfo> {
        GetAppInfoImp()
    }
}