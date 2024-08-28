package com.mtfe.rememberwidget.ext

import android.widget.Toast
import com.mtfe.rememberwidget.mContext



object ToastManager {
    private var lastToastMessage: String? = null
    private var currentToast: Toast? = null

    fun showToast(ctx: Context, str: String) {
        if (str == lastToastMessage && currentToast != null) {
            // 如果消息相同且当前Toast存在，则延长显示时间
            currentToast?.duration = Toast.LENGTH_SHORT
            currentToast?.show()
        } else {
            // 如果消息不同，则创建新的Toast
            lastToastMessage = str
            currentToast = Toast.makeText(ctx, str, Toast.LENGTH_SHORT)
            currentToast?.show()
        }
    }
}
fun showToast(str:String) {
    Toast.makeText(mContext,str, Toast.LENGTH_SHORT).show()
}



fun showEditTipToast() {
    Toast.makeText(mContext,"Please complete the information first.", Toast.LENGTH_SHORT).show()
}
fun showSubmitSuccessToast() {
   Toast.makeText(mContext,"Submit Success.", Toast.LENGTH_SHORT).show()
}
fun showRemovedToast() {
    Toast.makeText(mContext,"Removed.", Toast.LENGTH_SHORT).show()
}
fun showRemoveSuccessToast() {
    Toast.makeText(mContext,"Remove Success.", Toast.LENGTH_SHORT).show()
}
fun showAddedToast() {
    Toast.makeText(mContext,"Added.", Toast.LENGTH_SHORT).show()
}
fun showAddSuccessToast() {
    Toast.makeText(mContext,"Add Success.", Toast.LENGTH_SHORT).show()
}
fun showEditedToast() {
    Toast.makeText(mContext,"Edited.", Toast.LENGTH_SHORT).show()
}
fun showEditSuccessToast() {
    Toast.makeText(mContext,"Edit Success.", Toast.LENGTH_SHORT).show()
}

