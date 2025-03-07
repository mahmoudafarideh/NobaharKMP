package m.a.nobahar.storage

import androidx.compose.ui.graphics.ImageBitmap

interface ImageBitmapSaver {
    fun savePhoto(bitmap: ImageBitmap)
}