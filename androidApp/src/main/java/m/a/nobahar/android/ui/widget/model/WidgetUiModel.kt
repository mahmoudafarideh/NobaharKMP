package m.a.nobahar.android.ui.widget.model

import m.a.nobahar.domain.model.LoadableData
import m.a.nobahar.domain.model.NotLoaded

data class WidgetUiModel(
    val poemVerse: LoadableData<WidgetPoemVerseUiModel> = NotLoaded
)