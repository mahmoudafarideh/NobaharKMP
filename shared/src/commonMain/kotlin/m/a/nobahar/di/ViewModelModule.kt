package m.a.nobahar.di

import m.a.nobahar.ui.artwork.screen.ArtworkViewModel
import m.a.nobahar.ui.book.screen.BookViewModel
import m.a.nobahar.ui.home.screen.HomeViewModel
import m.a.nobahar.ui.main.PoemPlayerViewModel
import m.a.nobahar.ui.omen.screen.OmenViewModel
import m.a.nobahar.ui.poem.screen.PoemViewModel
import m.a.nobahar.ui.poet.screen.PoetViewModel
import m.a.nobahar.ui.search.model.SearchBookUiModel
import m.a.nobahar.ui.search.screen.SearchViewModel
import m.a.nobahar.ui.shared.model.PoetUiModel
import m.a.nobahar.ui.splash.screen.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module


val viewModelModules = module {
    viewModel {
        HomeViewModel(get(), get())
    }
    viewModel {
        ArtworkViewModel(get())
    }
    viewModel {
        PoemPlayerViewModel(get())
    }
    viewModel {
        OmenViewModel(get())
    }
    viewModel {
        SplashViewModel(get())
    }
    viewModel { (poet: PoetUiModel) ->
        PoetViewModel(poet, get(), get())
    }
    viewModel { (poet: PoetUiModel, bookId: Long) ->
        BookViewModel(poet, bookId, get())
    }
    viewModel { (poemId: Long) ->
        PoemViewModel(poemId, get(), get())
    }
    viewModel { (poetUiModel: PoetUiModel?, book: SearchBookUiModel?) ->
        SearchViewModel(poetUiModel, book, get())
    }
}
