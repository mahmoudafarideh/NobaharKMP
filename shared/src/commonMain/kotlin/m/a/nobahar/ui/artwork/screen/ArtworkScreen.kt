package m.a.nobahar.ui.artwork.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import m.a.nobahar.ui.capture.CaptureController
import m.a.nobahar.ui.capture.rememberCaptureController
import m.a.nobahar.ui.artwork.component.ArtworkAppBar
import m.a.nobahar.ui.artwork.component.ArtworkHorizontalContent
import m.a.nobahar.ui.artwork.component.ArtworkVerticalContent
import m.a.nobahar.ui.artwork.model.ArtFontUiModel
import m.a.nobahar.ui.artwork.model.ArtSavingState
import m.a.nobahar.ui.artwork.model.ArtScreenUiModel
import m.a.nobahar.ui.artwork.model.ArtTabUiModel
import m.a.nobahar.ui.shared.ui.LocalWindowSize
import m.a.nobahar.ui.shared.ui.NobaharPreview
import m.a.nobahar.ui.theme.PoemThemePreview
import org.jetbrains.compose.resources.DrawableResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtworkScreen(
    firstVerse: String,
    secondVerse: String,
    poetName: String,
    state: ArtScreenUiModel,
    onTabClick: (ArtTabUiModel.Tab) -> Unit,
    onFontClick: (ArtFontUiModel.Font) -> Unit,
    onFontSizeChange: (Int) -> Unit,
    onColorClick: (Color) -> Unit,
    onSaveButtonClick: () -> Unit,
    onMatnnegarClick: () -> Unit,
    onBackgroundClick: (DrawableResource) -> Unit,
    modifier: Modifier = Modifier,
    captureController: CaptureController
) {
    val windowSize = LocalWindowSize.current
    val scaffoldState = rememberBottomSheetScaffoldState()
    Scaffold(
        topBar = {
            if (windowSize.widthSizeClass == WindowWidthSizeClass.Compact) {
                ArtworkAppBar(state, onSaveButtonClick)
            }
        },
        modifier = modifier
    ) { contentPadding ->
        when (windowSize.widthSizeClass) {
            WindowWidthSizeClass.Compact -> {
                ArtworkVerticalContent(
                    state = state,
                    onTabClick = onTabClick,
                    onFontClick = onFontClick,
                    onFontSizeChange = onFontSizeChange,
                    onColorClick = onColorClick,
                    onBackgroundClick = onBackgroundClick,
                    scaffoldState = scaffoldState,
                    firstVerse = firstVerse,
                    secondVerse = secondVerse,
                    poetName = poetName,
                    captureController = captureController,
                    modifier = Modifier.padding(contentPadding),
                    onMatnnegarClick = onMatnnegarClick
                )
            }

            WindowWidthSizeClass.Expanded -> {
                ArtworkHorizontalContent(
                    state = state,
                    onTabClick = onTabClick,
                    onFontClick = onFontClick,
                    onFontSizeChange = onFontSizeChange,
                    onColorClick = onColorClick,
                    onBackgroundClick = onBackgroundClick,
                    firstVerse = firstVerse,
                    secondVerse = secondVerse,
                    poetName = poetName,
                    onSaveButtonClick = onSaveButtonClick,
                    captureController = captureController,
                    modifier = Modifier.padding(contentPadding),
                    onMatnnegarClick = onMatnnegarClick
                )
            }
        }
    }
}


@NobaharPreview
@Composable
private fun ArtworkScreenPreview() {
    PoemThemePreview {
        ArtworkScreen(
            firstVerse = "اَلا یا اَیُّهَا السّاقی اَدِرْ کَأسَاً و ناوِلْها",
            secondVerse = "که عشق آسان نُمود اوّل ولی افتاد مشکل\u200Cها",
            poetName = "حافظ",
            state = ArtScreenUiModel.default.copy(
                savingState = ArtSavingState.Failed
            ),
            onTabClick = {},
            onFontClick = {},
            onFontSizeChange = {},
            onColorClick = {},
            onBackgroundClick = {},
            onSaveButtonClick = {},
            onMatnnegarClick = {},
            captureController = rememberCaptureController()
        )
    }
}