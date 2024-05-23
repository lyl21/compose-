package com.mtfe.rememberwidget.ext

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatSecondsToHMS(seconds: Int): String {
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60
    val secs = seconds % 60

    return String.format("%02d:%02d:%02d", hours, minutes, secs)
}


fun dateStringToTimestamp(dateString: String, pattern: String): Long {
    val dateFormat = SimpleDateFormat(pattern)
    val date = dateFormat.parse(dateString)
    return date.time
}

fun timestampToDateString(format: String="yyyy-MM-dd HH:mm", timestamp:Long= System.currentTimeMillis()): String {
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    return sdf.format(Date(timestamp))
}

