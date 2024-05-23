package com.mtfe.rememberwidget.cpt


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.mtfe.rememberwidget.ext.debouncedClickable


@Preview
@Composable
fun EditDialogPreview() {

    var isOpenDialog by remember { mutableStateOf(false) }
    SideEffect {
        isOpenDialog=true
    }
    if (isOpenDialog) {
        EditDialog(
            "text",
            onConfirmButtonClick = {},
            onDismissRequest = { isOpenDialog = false })
    }
}

@Composable
fun EditDialog(
    etv: String,
    onConfirmButtonClick: (String) -> Unit,
    onDismissRequest: () -> Unit,
    isOutSideClose: Boolean = false
) {
    val etV = remember { mutableStateOf(etv) }
    Dialog(
        onDismissRequest = { onDismissRequest() },
        properties = DialogProperties(
            dismissOnBackPress = isOutSideClose,
            dismissOnClickOutside = isOutSideClose
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f)
                .background(Color.White, RoundedCornerShape(20.dp))
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Player nike name",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
            Spacer(modifier = Modifier.weight(1f))
            Edit_Wrap(etv = etV, hintStr = etV.value, imeAction = ImeAction.Done)
            Spacer(modifier = Modifier.weight(1f))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Confirm", fontSize = 18.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .debouncedClickable {
                            onConfirmButtonClick(etV.value)
                            onDismissRequest()
                        }
                        .padding(20.dp, 6.dp)
                )
            }


        }
    }
}

