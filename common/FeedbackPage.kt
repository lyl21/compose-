package com.nyvc.ledbanner.screen


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.nyvc.ledbanner.common.CommonPage
import com.nyvc.ledbanner.common.CommonPageBody
import com.nyvc.ledbanner.common.CommonPageHead
import com.nyvc.ledbanner.compment.BtSubmitText
import com.nyvc.ledbanner.compment.Edit_h120
import com.nyvc.ledbanner.ext.fromJson
import com.nyvc.ledbanner.ext.showEditTipToast
import com.nyvc.ledbanner.ext.showSubmitSuccessToast
import com.nyvc.ledbanner.ext.spGetStr
import com.nyvc.ledbanner.mNavController
import com.nyvc.ledbanner.screen.main.LEDInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Preview
@Composable
fun FeedbackPagePreview() {
    FeedbackPage()
}

@Composable
fun FeedbackPage() {
    val ledInfo = spGetStr("led").fromJson<LEDInfo>()
    val etv = remember { mutableStateOf("") }
    val rememberCoroutineScope = rememberCoroutineScope()
    CommonPage(
        head = {
            CommonPageHead("Feedback", isShowLtImg = true,
                middleColor = ledInfo.textColor,
                ltIconColor = ledInfo.textColor,
            )
        },
        body = {
            CommonPageBody {
                Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
                    Edit_h120(etv = etv, height = 260.dp, imeAction = ImeAction.Done)
                    Spacer(modifier = Modifier.weight(1f))
                    BtSubmitText {
                        if (etv.value.isEmpty()) {
                            showEditTipToast()
                            return@BtSubmitText
                        }
                        rememberCoroutineScope.launch {
                            delay(800)
                            showSubmitSuccessToast()
                            mNavController.popBackStack()
                        }
                    }
                }
            }
        }
    )
}

