package com.iuxc.customgc

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import java.io.File


fun getPicturesByPackageName(context: Context): Flow<List<Uri>> = flow {
    val images = mutableListOf<Uri>()
    val projection = arrayOf(
        MediaStore.Images.Media._ID,
        MediaStore.Images.Media.DISPLAY_NAME
    )
    val selection = "${MediaStore.Images.Media.DATA} like '%/${context.packageName}/%'"

    val query = context.contentResolver.query(
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        projection,
        selection,
        null,
        "${MediaStore.Images.Media.DATE_ADDED} DESC"
    )

    query?.use { cursor ->
        val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
        while (cursor.moveToNext()) {
            val id = cursor.getLong(idColumn)
            val contentUri = ContentUris.withAppendedId(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                id
            )
            images += contentUri
        }
        emit(images)
    }
}.flowOn(Dispatchers.IO)


fun delImage(context: Context, imageUris: List<Uri>): Flow<Pair<Int, Int>> = flow {
    val contentResolver = context.contentResolver
    val selection = MediaStore.Images.Media._ID + " = ?"
    var deletedSuccessCount = 0
    var deletedFailedCount = 0
    Log.e("xxx", "delImage:${imageUris.size} ")
    Log.e("xxx", "delImage:${imageUris} ")
    for (imageUri in imageUris) {
        val selectionArgs = arrayOf(imageUri.lastPathSegment)
        val rowsDeleted = contentResolver.delete(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection, selectionArgs
        )
        if (rowsDeleted > 0) {
            deletedSuccessCount++
        } else {
            deletedFailedCount++
        }
    }
    emit(Pair(deletedSuccessCount, deletedFailedCount))
}.flowOn(Dispatchers.IO)
