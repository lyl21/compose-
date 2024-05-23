package com.iuxc.customgc


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.key.Key.Companion.W
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CmPage(head: @Composable () -> Unit?, body: @Composable () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
//                    .background(MC_255)
            ) {
                head()
                body()
            }
    }
}


@Composable
fun CmPageBody(content:@Composable () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
//            .padding(20.dp)
    ) {
        content()
    }
}


@Composable
fun CmPageHead(
    middleText: String = "",
    middleTextSize: Int = 26,
    middleColor: Color = Color.Black,
    bgColor: Color = Color.White,
    paddingHorizontal: Int = 20,
    isShowMiddle: Boolean = true,
    isShowDivider: Boolean = true,
    isShowLtImg: Boolean = false,
    isShowLtText: Boolean = false,
    ltImgResInt: Int = 0,
    ltIcon: ImageVector = Icons.Default.KeyboardArrowLeft,
    ltIconColor: Color = Color.Black,
    ltTextStr: String = "Back",
    ltTextSize: Int = 14,
    ltTextColor: Color = Color.Black,
    ltClick: () -> Unit = { },
    isShowRtImg: Boolean = false,
    isShowRtText: Boolean = false,
    rtImgResInt: Int = 0,
    rtIcon: ImageVector = Icons.Default.Settings,
    rtIconColor: Color = Color.Black,
    rtTextStr: String = "More",
    rtTextSize: Int = 14,
    rtTextColor: Color = Color.Black,
    rtClick: (() -> Unit)? = null,
) {
//    BackHandler(enabled = true, onBack = { ltClick() })

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(bgColor)
            .statusBarsPadding()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(bgColor)
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(bgColor)
                    .padding(start = paddingHorizontal.dp)
            ) {
                if (isShowLtImg) {
                    if (ltImgResInt > 0) {
                        Image(
                            painter = painterResource(id = ltImgResInt), contentDescription = "",
                            modifier = Modifier
                                .width(32.dp)
                                .height(32.dp)
                                .clickable {
                                    ltClick.invoke()
                                }
                        )
                    } else {
                        Image(
                            imageVector = ltIcon, contentDescription = "",
                            colorFilter = ColorFilter.tint(ltIconColor),
                            modifier = Modifier
                                .width(32.dp)
                                .height(32.dp)
                                .clickable {
                                    ltClick.invoke()
                                },
                        )
                    }
                }
                if (isShowLtText) {
                    Text(
                        text = ltTextStr,
                        fontSize = ltTextSize.sp,
                        fontWeight = FontWeight.Bold,
                        color = ltTextColor,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.clickable {
                            ltClick.invoke()
                        }
                    )
                }
            }

            if (isShowMiddle) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .weight(3f)
                        .fillMaxHeight()
                        .background(bgColor)
                        .padding(horizontal = paddingHorizontal.dp)
                ) {
                    Text(
                        text = middleText,
                        fontSize = middleTextSize.sp,
                        fontWeight = FontWeight.Bold,
                        color = middleColor,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .background(bgColor)
                    .padding(end = paddingHorizontal.dp)
            ) {
                if (isShowRtImg) {
                    if (rtImgResInt > 0) {
                        Image(
                            painter = painterResource(id = rtImgResInt), contentDescription = "",
                            modifier = Modifier
                                .width(32.dp)
                                .height(32.dp)
                                .clickable {
                                    rtClick?.invoke()
                                }
                        )
                    } else {
                        Image(
                            imageVector = rtIcon, contentDescription = "",
                            colorFilter = ColorFilter.tint(rtIconColor),
                            modifier = Modifier
                                .width(32.dp)
                                .height(32.dp)
                                .clickable(onClick = {
                                    rtClick?.invoke()
                                })
                        )
                    }
                }
                if (isShowRtText) {
                    Text(
                        text = rtTextStr,
                        fontSize = rtTextSize.sp,
                        fontWeight = FontWeight.Bold,
                        color = rtTextColor,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.clickable {
                            rtClick?.invoke()
                        }
                    )
                }
            }
        }
        if (isShowDivider) {
            Divider()
        }
    }


}


