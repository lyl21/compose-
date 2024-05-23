package com.mtfe.rememberwidget.cpt

import android.text.TextUtils.replace
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mtfe.rememberwidget.ext.removeExtraDots
import com.mtfe.rememberwidget.ext.showToast
import com.mtfe.rememberwidget.ui.theme.MC_255
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@Preview
@Composable
fun MainColorEditPreview() {
    val etv = remember {
        mutableStateOf("")
    }
//    Edit_Wrap("", etv)
}


@Composable
fun Edit_Wrap(
    etv: MutableState<String>,
    hintStr: String = "Please enter.",
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    modifier: Modifier = Modifier
        .fillMaxWidth()
//        .height(58.dp)
//        .border(2.dp, Color.Black, RoundedCornerShape(10.dp))
//        .background(Color.Transparent, RoundedCornerShape(10.dp)),
    , textAlign: TextAlign = TextAlign.Start
) {
    val rememberCoroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = etv.value) {
        when (keyboardType) {
            KeyboardType.Number, KeyboardType.Phone -> {
                etv.value = etv.value.replace(".", "")
            }

            KeyboardType.Decimal -> {
                rememberCoroutineScope.launch {
                    delay(2000)
                    if (etv.value.count { it == '.' } > 1) {
                        etv.value = removeExtraDots(etv.value)
                    } else if (etv.value.endsWith(".")) {
                        withContext(Dispatchers.Main) {
                            showToast("cannot end with .")
                        }
                        etv.value = etv.value.dropLast(1)
                    }
//                    if (etv.value == "0.0" || etv.value == "0") {
//                        withContext(Dispatchers.Main) {
//                            showToast("The input content must be greater than 0")
//                            etv.value = ""
//                        }
//                        return@launch
//                    }
                }
            }
        }

 
    }
    OutlinedTextField(
        placeholder = {
            Text(
                text = hintStr,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        value = etv.value,
        onValueChange = {
            etv.value = it
        },
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            cursorColor = Color.Black,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedContainerColor = MC_255,         //bg
            unfocusedContainerColor = MC_255,      //bg
            focusedIndicatorColor = Color.Transparent,          //boder
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 16.sp,
            textAlign = textAlign,
//            fontWeight = FontWeight.Bold,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
    )
}

@Composable
fun Edit_h58(
//    defaultValue: String = "60",
    etv: MutableState<String>,
    hintStr: String = "Please enter.",
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Next,
    height: Dp = 58.dp,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(height)
//        .border(2.dp, Color.Black, RoundedCornerShape(10.dp))
//        .background(Color.Transparent, RoundedCornerShape(10.dp)),
    , textAlign: TextAlign = TextAlign.Center,
    borderColor: Color = Color.Black,
    onClickActionDone:()->Unit={},
) {
    val localSoftwareKeyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = etv.value) {
        when (keyboardType) {
            KeyboardType.Number, KeyboardType.Phone -> {
                etv.value = etv.value.filter { it.isDigit() }
//                    .replace(".", "")
                if(etv.value.isNotEmpty()&&etv.value.toInt()>24){
                    showToast("The maximum cannot exceed 24.")
                    etv.value="24"
                }
            }

            KeyboardType.Decimal -> {
                delay(2000)
                etv.value= etv.value.filter {
                    !it.isWhitespace()
                }
                if (etv.value.count { it == '.' } > 1) {
                    etv.value = removeExtraDots(etv.value)
                }
                if (etv.value.startsWith(".")) {
                    withContext(Dispatchers.Main) {
                        showToast("cannot start with .")
                    }
                    etv.value=""
                }
                if (etv.value.endsWith(".")) {
                    withContext(Dispatchers.Main) {
                        showToast("cannot end with .")
                    }
                    etv.value = etv.value.dropLast(1)
                }
            }
        }
    }

    OutlinedTextField(
        label = {
            Text(
                text = hintStr,
                overflow = TextOverflow.Ellipsis,
            )
        },
        value = etv.value,
        onValueChange = {
            etv.value = it
        },
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            cursorColor = Color.White,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedContainerColor = Color.Transparent,    //bg
            unfocusedContainerColor = Color.Transparent,
//            focusedIndicatorColor = MainColor_3_255,          //boder
//            unfocusedIndicatorColor = MainColor_3_255,
//            focusedLabelColor = Color.White,
//            unfocusedLabelColor = Color.White,

        ),
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 16.sp,
            textAlign = textAlign,
            fontWeight = FontWeight.W500,
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(onDone = {
            onClickActionDone()
            localSoftwareKeyboardController?.hide()
        })
    )
}

@Composable
fun Edit_h120(
    etv: MutableState<String>,
    hintStr: String = "Please enter.",
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    height: Dp = 120.dp,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .height(height)
//        .border(2.dp, Color.Black, RoundedCornerShape(10.dp))
//        .background(Color.Transparent, RoundedCornerShape(10.dp)),
    , textAlign: TextAlign = TextAlign.Start

) {
    val rememberCoroutineScope = rememberCoroutineScope()
    OutlinedTextField(
        label = {
            Text(
                text = hintStr,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        value = etv.value,
        onValueChange = {
            when (keyboardType) {
                KeyboardType.Number, KeyboardType.Phone -> {
                    etv.value = it.replace(".", "")
                }

                KeyboardType.Decimal -> {
                    rememberCoroutineScope.launch {
                        etv.value = it
                        delay(2000)
                        if (etv.value.count { it == '.' } > 1) {
                            etv.value = removeExtraDots(etv.value)
                        } else if (etv.value.endsWith(".")) {
                            withContext(Dispatchers.Main) {
                                showToast("cannot end with .")
                            }
                            etv.value = it.dropLast(1)
                        }
//                    if (etv.value == "0.0" || etv.value == "0") {
//                        withContext(Dispatchers.Main) {
//                            showToast("The input content must be greater than 0")
//                            etv.value = ""
//                        }
//                        return@launch
//                    }
                    }
                }

                else -> {
                    etv.value = it
                }
            }
        },
        modifier = modifier,
        colors = TextFieldDefaults.colors(
            cursorColor = Color.Black,
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedContainerColor = Color.Transparent,    //bg
            unfocusedContainerColor = Color.Transparent,
//            focusedIndicatorColor = Color.Black,          //boder
//            unfocusedIndicatorColor = Color.Black
        ),
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 16.sp,
            textAlign = textAlign,
//            fontWeight = FontWeight.Bold,
        ),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
    )
}


