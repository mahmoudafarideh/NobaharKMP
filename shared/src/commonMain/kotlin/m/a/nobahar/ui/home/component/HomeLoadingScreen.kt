package m.a.nobahar.ui.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import m.a.nobahar.ui.home.model.CenturyUiModel
import m.a.nobahar.ui.shared.model.PoetUiModel
import m.a.nobahar.ui.shared.ui.LocalWindowSize
import m.a.nobahar.ui.shared.ui.NobaharPreview
import m.a.nobahar.ui.theme.PoemThemePreview
import nobahar.shared.generated.resources.Res
import nobahar.shared.generated.resources.categorize_by_century_title
import nobahar.shared.generated.resources.popular_poets_title
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomeLoadingScreen(
    modifier: Modifier = Modifier
) {
    val windowSize = LocalWindowSize.current
    Column(
        modifier = modifier
            .fillMaxSize()
            .shimmer()
            .padding(
                horizontal =
                    when (windowSize.widthSizeClass) {
                        WindowWidthSizeClass.Expanded -> 148.dp
                        else -> 0.dp
                    }
            ),
    ) {
        Spacer(
            Modifier.windowInsetsBottomHeight(
                WindowInsets.systemBars
            )
        )
        val centuries = remember {
            persistentListOf(
                CenturyUiModel("قرن سوم", true),
                CenturyUiModel("قرن چهارم", false),
                CenturyUiModel("قرن پنجم", false),
                CenturyUiModel("قرن ششم", false),
                CenturyUiModel("قرن هفتم", false),
            )
        }
        Spacer(modifier = Modifier.size(24.dp))
        val popularPoets = remember {
            persistentListOf(
                PoetUiModel("حافظ", "URL", 2),
                PoetUiModel("سعدی", "URL", 3),
                PoetUiModel("مولانا", "URL", 4),
                PoetUiModel("فردوسی", "URL", 5),
                PoetUiModel("خیام", "URL", 6),
                PoetUiModel("نظامی", "URL", 7),
            )
        }
        val poets = persistentListOf(
            PoetUiModel("حافظ", "URL", 2)
        )
        Text(
            text = stringResource(Res.string.popular_poets_title),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 24.dp),
            color = MaterialTheme.colorScheme.onBackground
        )
        PoetsGrid(popularPoets)
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = stringResource(Res.string.categorize_by_century_title),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 24.dp),
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.size(16.dp))
        CenturyChips(
            labels = centuries,
            modifier = Modifier,
            onClick = {}
        )
        PoetsGrid(poets)
    }
}

@Composable
private fun PoetsGrid(
    popularPoets: ImmutableList<PoetUiModel>,
    modifier: Modifier = Modifier
) {
    val windowSize = LocalWindowSize.current
    val cells = remember {
        when (windowSize.widthSizeClass) {
            WindowWidthSizeClass.Compact -> 3
            WindowWidthSizeClass.Expanded -> 6
            WindowWidthSizeClass.Medium -> 3
            else -> 3
        }
    }
    LazyVerticalGrid(
        columns = GridCells.Fixed(cells),
        modifier = modifier
            .fillMaxWidth(),
        userScrollEnabled = false,
    ) {
        items(
            items = popularPoets,
            key = { it.id }
        ) {
            PoetLoadingBox(
                poetUiModel = it,
                modifier = Modifier.padding(24.dp)
            )
        }
    }
}

@NobaharPreview
@Composable
private fun HomeLoadingScreenPreview() {
    PoemThemePreview {
        HomeLoadingScreen()
    }
}