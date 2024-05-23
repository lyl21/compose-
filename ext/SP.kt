package com.nrq.elestickers.ext

import android.content.SharedPreferences

lateinit var SPManager: SharedPreferences
fun spGetStr(key: String): String {
    return SPManager.getString(key, "") ?: ""
}
fun spSetStr(key: String, value: String) {
    SPManager.edit().putString(key, value).apply()
}


val spGetStr: (Context,String?) -> String = { ctx,key ->
    ctx.getSharedPreferences(
        ctx.resources.getString(R.string.app_name), Context.MODE_PRIVATE
    )
        .getString(key?:ctx.resources.getString(R.string.app_name), "") ?: ""
}
val spSetStr: (Context,String?, String) -> Unit = { ctx,key, value ->
    ctx.getSharedPreferences(
        ctx.resources.getString(R.string.app_name), Context.MODE_PRIVATE
    )
        .edit().putString(key?:ctx.resources.getString(R.string.app_name), value).apply()
}