package com.jrrzx.emergencyhelper.compment


import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun DatePickerPreview() {
    val isOpenDialog =  remember {
        mutableStateOf(true)
    }
    DatePickerDialogCpt(isOpenDialog,{})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDialogCpt(isOpenDialog: MutableState<Boolean>, onDateSelected: (Long) -> Unit) {
    if (isOpenDialog.value) {
        val datePickerState = rememberDatePickerState()
        val confirmEnabled = derivedStateOf { datePickerState.selectedDateMillis != null }
        DatePickerDialog(
            modifier = Modifier.scale( 0.9f),
            onDismissRequest = {
                isOpenDialog.value = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        isOpenDialog.value = false
                        onDateSelected(datePickerState.selectedDateMillis?:0)
                    },
                    enabled = confirmEnabled.value
                ) {
                    Text("Ok")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        isOpenDialog.value = false
                    }
                ) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

