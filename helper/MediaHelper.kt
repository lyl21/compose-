package com.jrrzx.emergencyhelper.helper

import android.content.Context
import android.os.Parcelable
import android.provider.MediaStore


object MediaHelper {
    private val MEDIA_COLUMNS = arrayOf(
        MediaStore.Images.Media._ID,
        MediaStore.Images.Media.DATA,
        MediaStore.Images.Media.DATE_ADDED,
        MediaStore.Images.Media.DISPLAY_NAME,
        MediaStore.Images.Media.SIZE,
        MediaStore.Images.Media.WIDTH,
        MediaStore.Images.Media.HEIGHT,
        MediaStore.Images.Media.MIME_TYPE,
        MediaStore.Images.Media.ORIENTATION,
        MediaStore.Video.VideoColumns.DURATION
    )

    fun getMediaList(context: Context): List<MediaItemInfo> {
        val mediaList: MutableList<MediaItemInfo> = ArrayList()
        val contentResolver = context.contentResolver
        val sortOrder = MediaStore.Images.Media.DATE_ADDED + " DESC"

        // Get images
        val imageCursor = contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            MEDIA_COLUMNS, null, null, sortOrder
        )
        if (imageCursor != null && imageCursor.moveToFirst()) {
            do {
                val item = MediaItemInfo()
                item.id =
                    (imageCursor.getLong(imageCursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)))
                item.filePath =
                    (imageCursor.getString(imageCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)))
                item.displayName = (
                        imageCursor.getString(
                            imageCursor.getColumnIndexOrThrow(
                                MediaStore.Images.Media.DISPLAY_NAME
                            )
                        )
                        )
                item.mimeType =
                    (imageCursor.getString(imageCursor.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE)))
                item.orientation =
                    (imageCursor.getInt(imageCursor.getColumnIndexOrThrow(MediaStore.Images.Media.ORIENTATION)))
                item.duration =
                    (imageCursor.getLong(imageCursor.getColumnIndexOrThrow(MediaStore.Video.VideoColumns.DURATION)))
                item.dateTime =
                    imageCursor.getString(imageCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED))

                item.width =
                    (imageCursor.getInt(imageCursor.getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH)))
                item.height =
                    (imageCursor.getInt(imageCursor.getColumnIndexOrThrow(MediaStore.Images.Media.HEIGHT)))

                val sizeInBytes =
                    imageCursor.getLong(imageCursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE))
                item.size = String.format("%.2f", (sizeInBytes.toDouble() / 1024))

                mediaList.add(item)
            } while (imageCursor.moveToNext())
            imageCursor.close()
        }

        // Get videos
        val videoCursor = contentResolver.query(
            MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
            MEDIA_COLUMNS, null, null, sortOrder
        )
        if (videoCursor != null && videoCursor.moveToFirst()) {
            do {
                val item = MediaItemInfo()
                item.id =
                    (videoCursor.getLong(videoCursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)))
                item.filePath =
                    (videoCursor.getString(videoCursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)))
                item.displayName = (
                        videoCursor.getString(
                            videoCursor.getColumnIndexOrThrow(
                                MediaStore.Images.Media.DISPLAY_NAME
                            )
                        )
                        )
                val sizeInBytes =
                    videoCursor.getLong(videoCursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE))
                item.size = String.format("%.2f", (sizeInBytes.toDouble() / 1024))

                item.mimeType =
                    (videoCursor.getString(videoCursor.getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE)))
                item.duration =
                    (videoCursor.getLong(videoCursor.getColumnIndexOrThrow(MediaStore.Video.VideoColumns.DURATION)))
                mediaList.add(item)
            } while (videoCursor.moveToNext())
            videoCursor.close()
        }
        return mediaList
    }
}

data class MediaItemInfo(
    var id: Long? = 0,
    var filePath: String? = "",
    var displayName: String? = "",
    var dateTime: String? = "",
    var mimeType: String? = "",
    var size: String? = "0",
    var width: Int? = 0,
    var height: Int? = 0,
    var orientation: Int? = 0,
    var duration: Long? = 0,
)