package com.jrrzx.emergencyhelper.helper

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.provider.MediaStore
import android.widget.Toast

fun saveImgToSysGallery(context: Context, bitmap: Bitmap, displayName: String, description: String) {
    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.DISPLAY_NAME, displayName)
        put(MediaStore.Images.Media.DESCRIPTION, description)
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
    }
    val resolver = context.contentResolver
    val imageUri = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

    imageUri?.let { uri ->
        resolver.openOutputStream(uri)?.use { outputStream ->
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            Toast.makeText(context, "Image saved to Gallery.", Toast.LENGTH_SHORT).show()
        }
    }
}