package com.nrq.elestickers.ext

import android.annotation.SuppressLint
import android.text.Html
import android.text.Spanned



fun String.decodeUnicodeToList(): List<String> {
    // 使用正则表达式匹配 Unicode 字符，并将其解码为字符串
    val regex = Regex("\\\\u([0-9a-fA-F]{5})")
    val matches = regex.findAll(this)
    val resultList = mutableListOf<String>()
    for (match in matches) {
        val unicodeValue = match.groupValues[1]
        val charCode = unicodeValue.toInt(16)
        val char = Character.toChars(charCode)
        resultList.add(char.joinToString(separator = ""))
    }
    return resultList
}

fun String.toHtml(@SuppressLint("InlinedApi") flag: Int = Html.FROM_HTML_MODE_LEGACY): Spanned {
    return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        Html.fromHtml(this, flag)
    } else {
        Html.fromHtml(this)
    }
}

fun removeExtraDots(value: String,tagChar:Char='.'): String {
    var dotFound = false
    val result = StringBuilder()
    for (char in value) {
        if (char == tagChar && !dotFound) {
            dotFound = true
            result.append(char)
        } else if (char != tagChar) {
            result.append(char)
        }
    }
    return result.toString()
}
