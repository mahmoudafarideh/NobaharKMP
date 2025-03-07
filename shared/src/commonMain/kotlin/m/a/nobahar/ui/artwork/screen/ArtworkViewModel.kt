package m.a.nobahar.ui.artwork.screen

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import m.a.nobahar.storage.ImageBitmapSaver
import m.a.nobahar.ui.artwork.model.ArtFontUiModel
import m.a.nobahar.ui.artwork.model.ArtSavingState
import m.a.nobahar.ui.artwork.model.ArtScreenUiModel
import m.a.nobahar.ui.artwork.model.ArtTabUiModel
import m.a.nobahar.ui.shared.BaseViewModel
import org.jetbrains.compose.resources.DrawableResource

class ArtworkViewModel(
    private val bitmapSaver: ImageBitmapSaver
) :
    BaseViewModel<ArtScreenUiModel>(ArtScreenUiModel.default) {

    fun tabClicked(tab: ArtTabUiModel.Tab) {
        updateState {
            copy(
                tabs = tabs.map {
                    it.copy(selected = tab == it.tab)
                }.toImmutableList()
            )
        }
    }

    fun fontClicked(font: ArtFontUiModel.Font) {
        updateState {
            copy(
                fonts = fonts.map {
                    it.copy(selected = font == it.font)
                }.toImmutableList()
            )
        }
    }

    fun fontSizeChanged(size: Int) {
        updateState {
            copy(
                fontSizes = fontSizes.map {
                    it.copy(selected = it.size.progress == size)
                }.toImmutableList()
            )
        }
    }

    fun colorClicked(color: Color) {
        updateState {
            copy(
                colors = colors.map {
                    it.copy(selected = color == it.color)
                }.toImmutableList()
            )
        }
    }

    fun backgroundClicked(id: DrawableResource) {
        updateState {
            copy(
                backgrounds = backgrounds.map {
                    it.copy(selected = id == it.image)
                }.toImmutableList()
            )
        }
    }

    fun saveButtonClicked() {
        updateState {
            copy(savingState = ArtSavingState.Saving)
        }
    }

    fun savingFailed() {
        updateState {
            copy(savingState = ArtSavingState.None)
        }
    }

    fun bitmapLoaded(bitmap: ImageBitmap) {
        viewModelScope.launch(Dispatchers.Default) {
            runCatching {
                bitmapSaver.savePhoto(bitmap)
            }.onFailure {
                updateState {
                    copy(savingState = ArtSavingState.Failed)
                }
            }.onSuccess {
                updateState {
                    copy(savingState = ArtSavingState.Saved)
                }
            }
        }
    }

}