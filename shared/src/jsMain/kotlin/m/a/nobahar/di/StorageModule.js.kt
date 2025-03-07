package m.a.nobahar.di

import com.russhwolf.settings.StorageSettings
import m.a.nobahar.api.storage.PrefStorage
import m.a.nobahar.domain.storage.LocalStorage
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module

actual fun Module.storageModule(): KoinDefinition<LocalStorage> {
    return single {
        PrefStorage(StorageSettings())
    }
}