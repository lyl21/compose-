package com.mtfe.rememberwidget.ext

import android.widget.Toast
import com.mtfe.rememberwidget.mContext


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

