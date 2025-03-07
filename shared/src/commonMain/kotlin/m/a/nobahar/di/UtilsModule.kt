package m.a.nobahar.di

import m.a.nobahar.api.helper.GetAppInfo
import m.a.nobahar.storage.ImageBitmapSaver
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun Module.imageSaverModule(): KoinDefinition<ImageBitmapSaver>
expect fun Module.appInfoModule(): KoinDefinition<GetAppInfo>

val utilModules = module {
    imageSaverModule()
    appInfoModule()
}