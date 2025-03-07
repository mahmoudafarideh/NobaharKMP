package m.a.nobahar.ui.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import m.a.nobahar.ui.shared.ui.NobaharPreview
import m.a.nobahar.ui.theme.PoemThemePreview
import nobahar.shared.generated.resources.Res
import nobahar.shared.generated.resources.search_placeholder
import nobahar.shared.generated.resources.search_poet_book_placeholder
import nobahar.shared.generated.resources.search_poet_poems_placeholder
import org.jetbrains.compose.resources.stringResource

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun SearchInput(
    term: String,
    poetName: String?,
    bookName: String?,
    onTermChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    ProvideTextStyle(value = MaterialTheme.typography.bodyMedium) {
        SearchBarDefaults.InputField(
            query = term,
            onSearch = {},
            expanded = false,
            onExpandedChange = {},
            placeholder = {
                Text(
                    text = searchPlaceholder(poetName, bookName),
                    style = MaterialTheme.typography.bodySmall
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            },
            interactionSource = null,
            onQueryChange = {
                onTermChange(it)
            },
            modifier = modifier
                .fillMaxWidth()
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondaryContainer),
        )
    }
}

@Composable
private fun searchPlaceholder(poetName: String?, bookName: String?): String = when {
    poetName != null && bookName != null -> stringResource(
        Res.string.search_poet_book_placeholder,
        bookName,
        poetName,
    )

    poetName != null -> stringResource(
        Res.string.search_poet_poems_placeholder,
        poetName
    )

    else -> stringResource(Res.string.search_placeholder)
}

@NobaharPreview
@Composable
private fun SearchBarPreview() {
    PoemThemePreview {
        SearchInput(
            term = "",
            onTermChange = {

            },
            poetName = "حافظ",
            bookName = "غزلیات"
        )
    }
}