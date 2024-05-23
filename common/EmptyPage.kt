package com.hkl.emjpps.common

import androidx.compose.material3.ElevatedButton
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hkl.emjpps.ext.debouncedClickable
import com.hkl.emjpps.R

@Composable
fun EmptyPage(
    @DrawableRes img: Int = R.drawable.ic_launcher_foreground,
    textStr: String = "Empty data",
    onClickPage: () -> Unit?={}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .debouncedClickable {
//                onClickPage()
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (img != R.drawable.ic_launcher_foreground) {
            Image(
                painter = painterResource(id = img),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(128.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
//        ElevatedButton(
//            onClick = {
//                onClickPage()
//            }
//        ) {
            Text(
                text = textStr,
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
//            fontFamily = FontFamily.Cursive,
//            fontStyle = FontStyle.Italic
            )
//        }

    }
}