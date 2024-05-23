package com.ntrxr.multitimer.cpt

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
import com.ntrxr.multitimer.ext.debouncedClickable
import com.ntrxr.multitimer.R
import com.ntrxr.multitimer.ui.theme.Black_255

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
                onClickPage()
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
        Text(
            text = textStr,
            color = Black_255,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Cursive,
            fontStyle = FontStyle.Italic
        )
    }
}