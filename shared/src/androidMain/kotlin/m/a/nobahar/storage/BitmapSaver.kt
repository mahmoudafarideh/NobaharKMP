package m.a.nobahar.storage

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.os.Environment
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import m.a.nobahar.R
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat

class BitmapSaver(private val context: Context): ImageBitmapSaver {

    @SuppressLint("SimpleDateFormat")
    override fun savePhoto(bitmap: ImageBitmap) {
        val imagesDir = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
            context.resources.getString(R.string.app_name)
        )
        if (!imagesDir.exists()) {
            imagesDir.mkdirs()
        }
        val formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss")
        val fileName =
            context.resources.getString(R.string.app_name) + "_" + formatter.format(java.util.Date()) + ".png"

        val imageFile = File(imagesDir, fileName)

        FileOutputStream(imageFile).use { out ->
            bitmap.asAndroidBitmap().compress(Bitmap.CompressFormat.PNG, 100, out)
        }

        runCatching {
            MediaScannerConnection.scanFile(
                context,
                arrayOf(imageFile.path),
                null
            ) { _, _ -> }
        }
    }
}