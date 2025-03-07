package m.a.nobahar.di

import m.a.nobahar.domain.storage.LocalStorage
import org.koin.core.definition.KoinDefinition
import org.koin.core.module.Module
import org.koin.dsl.module

expect fun Module.storageModule(): KoinDefinition<LocalStorage>

val storageModules = module { storageModule() }