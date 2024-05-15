package com.example.myganesha.APICalling.EcoFriendly

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

object BitmapUtils {

    fun getUriFromBitmap(context: Context, bitmap: Bitmap): Uri {
        val cachePath = File(context.cacheDir, "images")
        cachePath.mkdirs()
        val file = File(cachePath, "image.jpg")
        val fileOutputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
        fileOutputStream.flush()
        fileOutputStream.close()
        return FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
    }
}