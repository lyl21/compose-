package com.jrrzx.emergencyhelper.compment


import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun BottomSheetPreview() {
//    BottomSheet()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun  LLLBottomSheet(isShowBottomSheet: MutableState<Boolean>,content: @Composable () -> Unit ) {
    ModalBottomSheet(
        sheetState = rememberModalBottomSheetState(),
        onDismissRequest = { isShowBottomSheet.value = false },
    ) {
        content()
    }

}

