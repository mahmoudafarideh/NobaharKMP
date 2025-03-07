package m.a.nobahar.di

import com.russhwolf.settings.SharedPreferencesSettings
import m.a.nobahar.api.storage.PrefStorage
import m.a.nobahar.domain.storage.LocalStorage
import org.koin.android.ext.koin.androidApplication
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module

actual fun Module.storageModule(): KoinDefinition<LocalStorage> {
    return single {
        PrefStorage(SharedPreferencesSettings.Factory(androidApplication()).create("nobahar"))
    }
}