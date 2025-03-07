package m.a.nobahar.di

import m.a.nobahar.api.repository.BookRepositoryImp
import m.a.nobahar.api.repository.CenturyPoetsRepositoryImp
import m.a.nobahar.api.repository.HomeCommunicationRepositoryImp
import m.a.nobahar.api.repository.PoemRepositoryImp
import m.a.nobahar.api.repository.PoemSearchRepositoryImp
import m.a.nobahar.api.repository.PoetRepositoryImp
import m.a.nobahar.api.repository.RandomRepositoryImp
import m.a.nobahar.api.repository.SplashRepositoryImp
import m.a.nobahar.domain.repository.BookRepository
import m.a.nobahar.domain.repository.CenturyPoetsRepository
import m.a.nobahar.domain.repository.HomeCommunicationRepository
import m.a.nobahar.domain.repository.PoemRepository
import m.a.nobahar.domain.repository.PoetRepository
import m.a.nobahar.domain.repository.RandomRepository
import m.a.nobahar.domain.repository.SearchRepository
import m.a.nobahar.domain.repository.SplashRepository
import org.koin.dsl.module

val repositoryModule = module {

    single<CenturyPoetsRepository> {
        CenturyPoetsRepositoryImp(get())
    }

    single<PoetRepository> {
        PoetRepositoryImp(get())
    }

    single<CenturyPoetsRepository> {
        CenturyPoetsRepositoryImp(get())
    }

    single<PoetRepository> {
        PoetRepositoryImp(get())
    }

    single<SearchRepository> {
        PoemSearchRepositoryImp(get())
    }

    single<RandomRepository> {
        RandomRepositoryImp(get(), get())
    }

    single<PoemRepository> {
        PoemRepositoryImp(get())
    }

    single<BookRepository> {
        BookRepositoryImp(get())
    }

    single<SplashRepository> {
        SplashRepositoryImp(get(), get(), get(), get())
    }

    single<HomeCommunicationRepository> {
        HomeCommunicationRepositoryImp()
    }

}