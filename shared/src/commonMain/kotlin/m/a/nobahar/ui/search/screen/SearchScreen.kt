package m.a.nobahar.ui.search.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import m.a.nobahar.domain.model.InitialFailed
import m.a.nobahar.domain.model.InitialLoading
import m.a.nobahar.ui.search.components.SearchAppBar
import m.a.nobahar.ui.search.components.SearchCharacterLimitInfo
import m.a.nobahar.ui.search.components.SearchNoResultInfo
import m.a.nobahar.ui.search.components.SearchResultList
import m.a.nobahar.ui.search.components.SearchResultShimmerList
import m.a.nobahar.ui.search.model.SearchScreenUiModel
import m.a.nobahar.ui.shared.components.FetchingDataFailed
import m.a.nobahar.ui.shared.model.PoetUiModel
import m.a.nobahar.ui.shared.ui.NobaharPreview
import m.a.nobahar.ui.theme.PoemThemePreview

@Composable
fun SearchScreen(
    state: SearchScreenUiModel,
    onTermChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onRetryClick: () -> Unit,
    onListReachEnd: () -> Unit,
    onPoemClick: (PoetUiModel, Long) -> Unit,
    modifier: Modifier = Modifier,
) {
    val listState = rememberLazyListState()
    Scaffold(
        topBar = {
            SearchAppBar(
                onTermChange = onTermChange,
                term = state.term,
                onBackClick = onBackClick,
                bookName = state.bookUiModel?.name,
                poetName = state.poetUiModel?.nickname,
                listState = listState,
                modifier = Modifier,
            )
        },
        modifier = modifier
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (state.shouldShowSearchLimit) {
                SearchCharacterLimitInfo(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                state.result.data?.let {
                    if (it.isNotEmpty()) {
                        SearchResultList(
                            result = it,
                            onClick = { poet, poemId ->
                                onPoemClick(poet, poemId)
                            },
                            modifier = Modifier,
                            resultState = state.result,
                            onListReachEnd = onListReachEnd,
                            onRetryClick = onRetryClick,
                            listState = listState
                        )
                    } else {
                        SearchNoResultInfo(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
                when (state.result) {
                    is InitialFailed -> {
                        FetchingDataFailed(
                            onRetryClick = onRetryClick,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                    is InitialLoading -> {
                        SearchResultShimmerList()
                    }

                    else -> {}
                }
            }
        }
    }
}

@NobaharPreview
@Composable
fun SearchScreenPreview() {
    PoemThemePreview {
        var term by remember { mutableStateOf("") }
        SearchScreen(
            onTermChange = {
                term = it
            },
            onBackClick = {},
            onRetryClick = { },
            onListReachEnd = { },
            onPoemClick = { _, _ -> },
            state = SearchScreenUiModel(
                poetUiModel = PoetUiModel.fixture,
                bookUiModel = null,
                term = term
            ),
        )
    }
}