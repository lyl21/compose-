package com.nrq.elestickers.util

import android.app.WallpaperManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast


fun saveImageToWallpaper(context: Context, bitmap: Bitmap) {
//fun saveImageToWallpaper(context: Context, bitmap: Bitmap): Uri? {
    val uri: Uri? = saveImageToDCIM(context, bitmap, false)
    if (uri != null) {
        setWallpaper(context, uri)
    } else {
        Toast.makeText(context, "Wallpaper setting failed.", Toast.LENGTH_SHORT).show()
    }
//    return uri
}

fun saveImageToWallpaper(context: Context, uri: Uri?) {
    if (uri != null) {
        setWallpaper(context, uri)
    } else {
        Toast.makeText(context, "Wallpaper setting failed.", Toast.LENGTH_SHORT).show()
    }
}

fun setWallpaper(context: Context?, uriPath: Uri?) {
    if (context == null || uriPath == null) return

    val intent: Intent
    if (isHuaweiRom) {
        try {
            val componentName =
                ComponentName("com.android.gallery3d", "com.android.gallery3d.app.Wallpaper")
            intent = Intent(Intent.ACTION_VIEW)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.setDataAndType(uriPath, "image/*")
            intent.putExtra("mimeType", "image/*")
            intent.component = componentName
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            defaultWay(context, uriPath)
        }
    } else if (isMiuiRom) {
        try {
            val componentName = ComponentName(
                "com.android.thememanager",
                "com.android.thememanager.activity.WallpaperDetailActivity"
            )
            intent = Intent("miui.intent.action.START_WALLPAPER_DETAIL")
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.setDataAndType(uriPath, "image/*")
            intent.putExtra("mimeType", "image/*")
            intent.component = componentName
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            defaultWay(context, uriPath)
        }
    } else if (isOppoRom) {
        try {
            val componentName = ComponentName(
                "com.oplus.wallpapers",
                "com.oplus.wallpapers.wallpaperpreview.PreviewStatementActivity"
            )
            intent = Intent(Intent.ACTION_VIEW)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.setDataAndType(uriPath, "image/*")
            intent.putExtra("mimeType", "image/*")
            intent.component = componentName
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            defaultWay(context, uriPath)
        }
    } else if (isVivoRom) {
        try {
            val componentName =
                ComponentName("com.vivo.gallery", "com.android.gallery3d.app.Wallpaper")
            intent = Intent(Intent.ACTION_VIEW)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.setDataAndType(uriPath, "image/*")
            intent.putExtra("mimeType", "image/*")
            intent.component = componentName
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            defaultWay(context, uriPath)
        }
    } else if (isOnePlusRom) {
        try {
            val componentName = ComponentName(
                "com.oplus.wallpapers",
                "com.oplus.wallpapers.wallpaperpreview.WallpaperPreviewActivity"
            )
            intent = Intent(Intent.ACTION_VIEW)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.setDataAndType(uriPath, "image/*")
            intent.putExtra("mimeType", "image/*")
            intent.component = componentName
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            defaultWay(context, uriPath)
        }
    } else {
        try {
            intent = WallpaperManager.getInstance(context)
                .getCropAndSetWallpaperIntent(uriPath)
            context.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
            defaultWay(context, uriPath)
        }
    }
}

private fun defaultWay(context: Context, uri: Uri) {
    val bitmap: Bitmap?
    try {
        bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
        if (bitmap != null) {
            WallpaperManager.getInstance(context).setBitmap(bitmap)
            Toast.makeText(context, "Setup successful.", Toast.LENGTH_SHORT).show()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}