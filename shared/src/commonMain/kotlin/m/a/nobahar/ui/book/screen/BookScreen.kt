package m.a.nobahar.ui.book.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.valentinilk.shimmer.shimmer
import kotlinx.collections.immutable.ImmutableList
import m.a.nobahar.domain.model.Failed
import m.a.nobahar.domain.model.LoadableData
import m.a.nobahar.domain.model.Loaded
import m.a.nobahar.domain.model.Loading
import m.a.nobahar.domain.model.NotLoaded
import m.a.nobahar.ui.LocalNavController
import m.a.nobahar.ui.book.components.BookItemsColumn
import m.a.nobahar.ui.book.components.PoemBioLoading
import m.a.nobahar.ui.book.model.BookSubItemUiModel
import m.a.nobahar.ui.book.navigation.BookRoute
import m.a.nobahar.ui.poem.navigation.PoemRoute
import m.a.nobahar.ui.search.navigation.SearchRoute
import m.a.nobahar.ui.shared.components.FetchingDataFailed
import m.a.nobahar.ui.shared.components.PoetAppBar
import m.a.nobahar.ui.shared.model.PoetUiModel
import m.a.nobahar.ui.shared.ui.NobaharPreview
import m.a.nobahar.ui.shared.ui.scrollShadow
import m.a.nobahar.ui.theme.PoemThemePreview

@Composable
fun BookScreen(
    poetUiModel: PoetUiModel,
    bookInfo: LoadableData<ImmutableList<BookSubItemUiModel>>,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier,
    bookId: Long,
    bookName: String
) {
    val state = rememberLazyListState()
    val navigation = LocalNavController.current
    Scaffold(
        topBar = {
            PoetAppBar(
                poetUiModel = poetUiModel,
                onBackClick = { navigation.popBackStack() },
                modifier = Modifier.scrollShadow(state),
                onSearchClick = {
                    navigation.navigate(
                        SearchRoute(
                            poetUiModel.id,
                            poetUiModel.name,
                            poetUiModel.nickname,
                            poetUiModel.profile,
                            bookId,
                            bookName
                        )
                    )
                },
            )
        },
        modifier = modifier
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .then(
                    when (bookInfo) {
                        is Loading -> Modifier.shimmer()
                        else -> Modifier
                    }
                )
                .padding(padding)
        ) {
            when (bookInfo) {
                Failed -> Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    FetchingDataFailed(onRetryClick = onRetryClick)
                }

                is Loaded -> {
                    BookItemsColumn(
                        poetInfo = bookInfo,
                        onBookClick = {
                            navigation.navigate(
                                BookRoute(
                                    id = poetUiModel.id,
                                    name = poetUiModel.name,
                                    nickName = poetUiModel.nickname,
                                    profile = poetUiModel.profile,
                                    bookId = it.id,
                                    bookName = it.label,
                                )
                            )
                        },
                        onPoemClick = {
                            navigation.navigate(
                                PoemRoute(poemId = it.id)
                            )
                        },
                        modifier = Modifier,
                        state = state
                    )
                }

                Loading -> PoemBioLoading(modifier = Modifier)

                NotLoaded -> {}
            }
        }
    }
}


@NobaharPreview
@Composable
fun BookScreenPreview() {
    PoemThemePreview {
        BookScreen(
            poetUiModel = PoetUiModel.fixture,
            bookInfo = Loading,
            onRetryClick = {},
            bookId = 1,
            bookName = "bookName",
        )
    }
}