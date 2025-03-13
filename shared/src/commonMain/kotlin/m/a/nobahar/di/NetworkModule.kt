package m.a.nobahar.di


import m.a.nobahar.api.contract.BookApi
import m.a.nobahar.api.contract.CenturyApi
import m.a.nobahar.api.contract.PoemApi
import m.a.nobahar.api.contract.PoetApi
import m.a.nobahar.api.contract.RandomApi
import m.a.nobahar.api.contract.SearchApi
import m.a.nobahar.api.contract.SplashApi
import m.a.nobahar.api.helper.AudioSyncHelper
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val apiModules = module {

    singleOf(::CenturyApi)
    singleOf(::PoetApi)
    singleOf(::SearchApi)
    singleOf(::RandomApi)
    singleOf(::PoemApi)
    singleOf(::BookApi)
    singleOf(::SplashApi)
    singleOf(::AudioSyncHelper)

}