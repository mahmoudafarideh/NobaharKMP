package m.a.nobahar.di

import m.a.nobahar.domain.repository.MediaPlayerRepository
import m.a.nobahar.repository.MediaPlayerRepositoryImp
import org.koin.core.module.Module
import org.koin.dsl.module

actual val mediaPlayerModule: Module = module {
    single<MediaPlayerRepository> {
        MediaPlayerRepositoryImp()
    }
}