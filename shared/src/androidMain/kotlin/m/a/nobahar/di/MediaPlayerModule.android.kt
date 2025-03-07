package m.a.nobahar.di

import androidx.media3.exoplayer.ExoPlayer
import m.a.nobahar.domain.repository.MediaPlayerRepository
import m.a.nobahar.repository.MediaPlayerRepositoryImp
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

actual val mediaPlayerModule: Module = module {
    single<MediaPlayerRepository> {
        MediaPlayerRepositoryImp(get(), get())
    }
    single {
        ExoPlayer.Builder(androidApplication()).build()
    }
}