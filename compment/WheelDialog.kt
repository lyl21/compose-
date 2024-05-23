package com.lll.demo

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Preview
@Composable
private fun WheelDialogPreview() {
    var isShowWheelDialog by remember {
        mutableStateOf(false)
    }
    SideEffect {
        isShowWheelDialog=true
    }
        if(isShowWheelDialog){
            WheelDialog(
                listOf(
                    "11",
                    "22",
                    "33",
                    "44",
                    "55",
                    "66",
                    "77",
                ),
                onDismissRequest = {isShowWheelDialog=false},
                onConfirmed = {
                    Log.e("xxx", "WheelDialogPreview:$it")
                }
            )
        }

}

@Composable
fun WheelDialog(
    list: List<String>,
    onDismissRequest: () -> Unit,
    onConfirmed:(String)->Unit
) {
    var selectedItem by remember { mutableStateOf("") }
    Dialog(
        onDismissRequest = {
            onDismissRequest()
        },
        properties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = false
        )
    ) {
        Column(
            Modifier
                .fillMaxWidth(0.9f)
                .wrapContentHeight()
                .background(Color.White, RoundedCornerShape(10.dp))
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            WheelTextPicker(list = list) {
                selectedItem=list[it]
            }
            Spacer(modifier = Modifier.height(20.dp))
            Divider(color = Color(245, 166, 110))
            Box(
                Modifier
                    .fillMaxWidth()
                    .clickable {
                        onConfirmed(selectedItem)
                    }
                    .padding(20.dp), Alignment.Center) {
                Image(
                    imageVector = Icons.Default.Check, contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    colorFilter = ColorFilter.tint(Color(245, 166, 110))
                )
            }
        }
    }
}
