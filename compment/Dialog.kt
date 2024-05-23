package com.mtfe.rememberwidget.cpt

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.google.gson.annotations.Until
import com.mtfe.rememberwidget.ui.theme.MC_255
import com.mtfe.rememberwidget.ui.theme.Red_255


@Composable
fun DialogOK(text: String, onDismissRequest: () -> Unit, isOutSideClose: Boolean = false) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = isOutSideClose,
            dismissOnClickOutside = isOutSideClose
        ),
        title = {
            Text(
                text = "Tips",
                fontWeight = FontWeight.W700,
            )
        },
        text = {
            Text(
                text = text,
                fontSize = 16.sp
            )
        },
        confirmButton = {
            TextButton(onClick = onDismissRequest) {
                Text("OK")
            }
        }
    )
}


@Composable
fun DialogFailed(
    title: String = "You Failed!",
    text: String = "Don't be discouraged and keep working hard.",
    onLtClick:()->Unit,
    onRtClick:()->Unit,
    onDismissRequest: () -> Unit,
    isOutSideClose: Boolean = false
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = isOutSideClose,
            dismissOnClickOutside = isOutSideClose
        ),
        title = {
            Text(
                text = title,
                fontSize = 24.sp,
                color = Red_255,
                fontWeight = FontWeight.Bold,
            )
        },
        text = {
            Text(
                text = text,
                fontSize = 20.sp,
            )
        },
        dismissButton = {
            TextButton(onClick = {
                onLtClick()
                onDismissRequest()
            }) {
                Text("Exit Game", color = Color.Black,fontSize = 18.sp)
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onRtClick()
                onDismissRequest()
            }) {
                Text("Continue", fontWeight = FontWeight.Bold, color = MC_255, fontSize = 18.sp)
            }
        }
    )
}

@Composable
fun DialogSuccess(
    title: String = "Bingo!",
    text: String = "Keep up the good work.",
    onDismissRequest: () -> Unit,
    onLtClick:()->Unit,
    onRtClick:()->Unit,
    isOutSideClose: Boolean = false
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = isOutSideClose,
            dismissOnClickOutside = isOutSideClose
        ),
        title = {
            Text(
                text = title,
                fontSize = 24.sp,
                color = Color.Green,
                fontWeight = FontWeight.Bold,
            )
        },
        text = {
            Text(
                text = text,
                fontSize = 20.sp,
            )
        },
        dismissButton = {
            TextButton(onClick = {
                onLtClick()
                onDismissRequest()
            }) {
                Text("Exit Game", color = Color.Black,fontSize = 18.sp)
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onRtClick()
                onDismissRequest()
            }) {
                Text("Continue", fontWeight = FontWeight.Bold, color = MC_255, fontSize = 18.sp)
            }
        }
    )
}