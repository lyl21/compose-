package com.mtfe.rememberwidget

import android.content.Context
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import java.util.Calendar

fun shareDrawableImg(context: Context, drawable: Drawable) {
    try {
        val bd = drawable as BitmapDrawable
        val bitmap = bd.bitmap
        val uri = Uri.parse(
            MediaStore.Images.Media.insertImage(
                context.contentResolver,
                bitmap,
                "IMG" + Calendar.getInstance().time,
                null
            )
        )
        shareImg(context, uri)
    } catch (e: Exception) {
        Toast.makeText(context, "Sharing failed.", Toast.LENGTH_SHORT).show()
    }
}

fun shareImg(context: Context, uri: Uri) {
    try {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "image/*"
            putExtra(Intent.EXTRA_STREAM, uri)
        }
        context.startActivity(
            Intent.createChooser(
                intent,
               "Share Image"
            )
        )
    } catch (e: Exception) {
        Toast.makeText(context, "Sharing failed.", Toast.LENGTH_SHORT).show()
    }
}

fun shareText(context: Context, text: String?) {
    //https://play.google.com/store/apps/details?id=com.xxx.xxx
    try {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text ?: "")
        }
        context.startActivity(
            Intent.createChooser(
                intent,
                "Share APP"
            )
        )
    } catch (e: Exception) {
        Toast.makeText(context, "Sharing failed.", Toast.LENGTH_SHORT).show()
    }
}

//分享到指定平台
//text/plain
//application/*
//image/*
//video/*
//audio/*
fun shareToPlatform(context: Context, text: String?) {
    try {
        val intent = Intent(Intent.ACTION_SEND).apply {
            `package` = "com.xxx.xxx"
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text ?: "")
        }
        context.startActivity(
            Intent.createChooser(
                intent,
                "Share Image"
            )
        )
    } catch (e: Exception) {
        Toast.makeText(context, "Sharing failed.", Toast.LENGTH_SHORT).show()
    }
}