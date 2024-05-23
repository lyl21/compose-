package com.nyvc.ledbanner.compment


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.github.skydoves.colorpicker.compose.AlphaSlider
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.ImageColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.nyvc.ledbanner.R
import com.nyvc.ledbanner.ext.debouncedClickable
import com.nyvc.ledbanner.ui.theme.Black_10
import com.nyvc.ledbanner.ui.theme.Black_120
import com.nyvc.ledbanner.ui.theme.Black_40
import com.nyvc.ledbanner.ui.theme.White_200
import com.nyvc.ledbanner.ui.theme.White_220
import com.nyvc.ledbanner.ui.theme.White_255
import kotlin.math.roundToInt
import kotlin.random.Random


@Preview
@Composable
fun ColorPickerPreview() {
    var isOpenDialog by remember { mutableStateOf(false) }
    if (isOpenDialog) {
        ColorPicker(
            onColorSelected = {},
            onConfirmButtonClick = {},
            onDismissRequest = { isOpenDialog = false })
    }
}


@Composable
fun ColorPicker(
    initColor:Color= Color( android.graphics.Color.parseColor("0XFFFFFFFF")),
    onColorSelected: (Color) -> Unit,
    onConfirmButtonClick: (Color) -> Unit,
    onDismissRequest: () -> Unit,
    isOutSideClose: Boolean = false
) {
    val controller = rememberColorPickerController()
    var chooseColor by remember {
        mutableStateOf(initColor)
    }
    var colorStr by remember {
        mutableStateOf(initColor.toString())
    }
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
                .fillMaxHeight(0.8f)
                .background(White_255, RoundedCornerShape(20.dp))
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Choose the color",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Monospace
            )
            Spacer(modifier = Modifier.height(20.dp))
            HsvColorPicker(
                modifier = Modifier
                    .size(200.dp),
                controller = controller,
                onColorChanged = { colorEnvelope: ColorEnvelope ->
                    colorStr=colorEnvelope.hexCode
                    onColorSelected(
                        Color(android.graphics.Color.parseColor("#${colorEnvelope.hexCode}"))
                    )
                    chooseColor =
                        Color(android.graphics.Color.parseColor("#${colorEnvelope.hexCode}"))
                }
            )
            Spacer(modifier = Modifier.height(40.dp))
            AlphaSlider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp),
                controller = controller,
                tileOddColor = Color.White,
                tileEvenColor = Color.Black
            )
            Spacer(modifier = Modifier.height(20.dp))
            BrightnessSlider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(35.dp),
                controller = controller,
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(text =colorStr, color = chooseColor, fontSize = 16.sp)
            AlphaTile(
                modifier = Modifier
                    .size(88.dp)
                    .clip(RoundedCornerShape(6.dp)),
                controller = controller,
                tileOddColor = Color.White,
                tileEvenColor = Color.Black
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(text = "Cancel", fontSize = 18.sp, color = Black_120,
                    modifier = Modifier
                        .debouncedClickable {
                            onDismissRequest()
                        }
                        .padding(20.dp, 6.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "Confirm", fontSize = 18.sp, fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .debouncedClickable {
                            onConfirmButtonClick(chooseColor)
                            onDismissRequest()
                        }
                        .padding(20.dp, 6.dp)
                )
            }


        }
    }


}

