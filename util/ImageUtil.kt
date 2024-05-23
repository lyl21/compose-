package com.nrq.elestickers.util

import android.content.ContentValues
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.text.format.DateUtils
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.widget.Toast
import androidx.core.content.FileProvider
import java.io.FileOutputStream


fun saveImageToDCIM(context: Context, drawable: Drawable, showToast: Boolean=true): Uri? {
    try {
        val bd = drawable as BitmapDrawable
        val bitmap = bd.bitmap
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            saveImage(context, bitmap, showToast)
        } else {
            saveImageToGallery10(context, bitmap, showToast)
        }
    } catch (e: Exception) {
        if (showToast) {
            Toast.makeText(context, "Image Save Fail.", Toast.LENGTH_SHORT).show()
        }
        return null
    }
}

fun saveImageToDCIM(context: Context, bitmap: Bitmap, showToast: Boolean=true): Uri? {
    return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
        saveImage(context, bitmap, showToast)
    } else {
        saveImageToGallery10(context, bitmap, showToast)
    }
}

// 保存图片
private fun saveImage(context: Context, bitmap: Bitmap?, showToast: Boolean=true): Uri? {
    if (bitmap == null) {
        return null
    }
    val uri: Uri?
    val file = saveImageToGallery(context, bitmap, showToast)
    uri = if (file == null) {
        null
    } else {
        FileProvider.getUriForFile(
            context,
            context.applicationContext.packageName + ".fileprovider",
            file
        )
    }
    return uri
}

/**
 * android 10 以下版本
 */
fun saveImageToGallery(context: Context?, image: Bitmap, showToast: Boolean=true): File? {
    // 首先保存图片
    val storePath =
        Environment.getExternalStorageDirectory().absolutePath + File.separator + "Photo"
    val appDir = File(storePath)
    if (!appDir.exists()) {
        appDir.mkdir()
    }
    val fileName = System.currentTimeMillis().toString() + ".jpg"
    val file = File(appDir, fileName)
    try {
        val fos = FileOutputStream(file)
        // 通过io流的方式来压缩保存图片
        val isSuccess = image.compress(Bitmap.CompressFormat.JPEG, 90, fos)
        fos.flush()
        fos.close()

        // 保存图片后发送广播通知更新数据库
        val uri = Uri.fromFile(file)
        context!!.sendBroadcast(Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri))
        return if (isSuccess) {
            if (showToast) {
                Toast.makeText(context, "Image Save Success.", Toast.LENGTH_SHORT).show()
            }
            file
        } else {
            if (showToast) {
                Toast.makeText(context, "Image Save Fail.", Toast.LENGTH_SHORT).show()
            }
            null
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }

    if (showToast) {
        Toast.makeText(context, "Image Save Fail.", Toast.LENGTH_SHORT).show()
    }
    return null
}


private fun saveImageToGallery10(context: Context, image: Bitmap, showToast: Boolean=true): Uri? {
    val mImageTime = System.currentTimeMillis()
    val imageDate: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date(mImageTime))
    val template = "${context.packageName}_%s.png"
    val mImageFileName = String.format(template, imageDate)
    val values = ContentValues()
    values.put(
        MediaStore.MediaColumns.RELATIVE_PATH,
        Environment.DIRECTORY_PICTURES + File.separator + context.packageName
    )
    values.put(MediaStore.MediaColumns.DISPLAY_NAME, mImageFileName)
    values.put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
    values.put(MediaStore.MediaColumns.DATE_ADDED, mImageTime / 1000)
    values.put(MediaStore.MediaColumns.DATE_MODIFIED, mImageTime / 1000)
    values.put(
        MediaStore.MediaColumns.DATE_EXPIRES,
        (mImageTime + DateUtils.DAY_IN_MILLIS) / 1000
    )
    values.put(MediaStore.MediaColumns.IS_PENDING, 1)
    val resolver = context.contentResolver
    val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
    try {
        resolver.openOutputStream(uri!!).use { out ->
            if (!image.compress(
                    Bitmap.CompressFormat.PNG, 100,
                    out!!
                )
            ) {
                if (showToast) {
                    Toast.makeText(context, "Image Save Fail.", Toast.LENGTH_SHORT).show()
                }
                return null
            }
        }

        values.clear()
        values.put(MediaStore.MediaColumns.IS_PENDING, 0)
        values.putNull(MediaStore.MediaColumns.DATE_EXPIRES)
        resolver.update(uri, values, null, null)
    } catch (e: IOException) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            resolver.delete(uri!!, null)
        }
        if (showToast) {
            Toast.makeText(context, "Image Save Fail.", Toast.LENGTH_SHORT).show()
        }
        return null
    }

    if (showToast) {
        Toast.makeText(context, "Image Save Success.", Toast.LENGTH_SHORT).show()
    }
    return uri
}
