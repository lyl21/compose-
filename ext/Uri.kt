package com.ntrxr.multitimer.ext

import android.content.Context
import android.net.Uri
import androidx.core.content.FileProvider
import java.io.File

fun filePathToUri(context: Context, filePath: String): Uri {
    val file = File(filePath)
    return FileProvider.getUriForFile(context, context.packageName + ".fileprovider", file)
}


fun uriToBitmap(uri: Uri): Bitmap? {
    val inputStream: InputStream = mContext.contentResolver.openInputStream(uri) ?: return null
    val drawable = Drawable.createFromStream(inputStream, uri.toString())
    return drawable?.toBitmap()
}


fun uriToCompressedImageBitmap(uri: Uri, quality: Int): ImageBitmap? {
    val inputStream: InputStream = mContext.contentResolver.openInputStream(uri) ?: return null
    val drawable = Drawable.createFromStream(inputStream, uri.toString())
    val bitmap = drawable?.toBitmap()
    val baos = ByteArrayOutputStream()
    bitmap?.compress(Bitmap.CompressFormat.JPEG, quality, baos)
    val compressedBitmap = BitmapFactory.decodeByteArray(baos.toByteArray(), 0, baos.size())
    baos.close()
    return compressedBitmap?.asImageBitmap()
}