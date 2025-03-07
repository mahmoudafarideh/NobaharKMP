package m.a.nobahar.di

import m.a.nobahar.api.helper.GetAppInfo
import m.a.nobahar.storage.BitmapSaver
import m.a.nobahar.storage.ImageBitmapSaver
import org.koin.android.ext.koin.androidApplication
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module

actual fun Module.imageSaverModule(): KoinDefinition<ImageBitmapSaver> {
    return single<ImageBitmapSaver> {
        BitmapSaver(androidApplication())
    }
}

actual fun Module.appInfoModule(): KoinDefinition<GetAppInfo> {
    TODO("Not yet implemented")
}