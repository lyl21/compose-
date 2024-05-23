package com.lll.demo

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.DisplayMode.Companion.Picker
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Picker
import androidx.wear.compose.material.rememberPickerState

@Preview
@Composable
fun WheelTextPickerPreview() {
    val list = listOf(
        "11",
        "22",
        "33",
        "44",
        "55",
        "66",
        "77",

    )
    Column(
        Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        WheelTextPicker(list) {
//            Log.e("xx", "WheelTextPickerPreview: ${list[it]}", )
        }
    }


}

@Composable
fun WheelTextPicker(
    list: List<String>,
    onSelected: (Int) -> Unit
) {
    var isOnSelected by remember {
        mutableStateOf(false)
    }
    val state = rememberPickerState(list.size,initiallySelectedOption=(list.size-1)/2)
    LaunchedEffect(key1 = state.selectedOption) {
//        state.scrollToOption(0)
//        Log.e("xx", "WheelTextPickerPreview: ${state.selectedOption}", )
        onSelected(state.selectedOption)
        isOnSelected=false
    }

    Picker(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
        ,
        state =state,
        contentDescription = null,
        separation = 20.dp,
        gradientColor = Color.Transparent,
        onSelected={
            isOnSelected=true
        },
    ) {
        Column (Modifier.fillMaxWidth()){

            if(!isOnSelected&&state.selectedOption==it){
                Divider()
            }
            Text(list[it], Modifier.fillMaxWidth(), textAlign = TextAlign.Center,
                fontSize = if(state.selectedOption==it)36.sp else 26.sp)
            if(!isOnSelected&&state.selectedOption==it){
                Divider()
            }
        }

    }
}