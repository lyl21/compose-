package com.jrrzx.emergencyhelper.ext

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import com.jrrzx.emergencyhelper.mContext

fun copyToClipboard(text: String,isShowToast:Boolean=true,toastString: String="Copy Success.") {
    val clipboardManager = mContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    clipboardManager.setPrimaryClip(ClipData.newPlainText("text", text))
    if(isShowToast){
        Toast.makeText(mContext, toastString, Toast.LENGTH_SHORT).show()
    }
}