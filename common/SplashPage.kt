package com.yeq.fhtfortune.page


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.yeq.fhtfortune.CmPage
import com.yeq.fhtfortune.CmPageBody
import com.yeq.fhtfortune.CmPageHead
import com.yeq.fhtfortune.R
import com.yeq.fhtfortune.mNavController
import com.yeq.fhtfortune.nav.PagesRouter
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Preview
@Composable
private fun SplashPagePreview() {
    SplashPage()
}

@Composable
fun SplashPage() {
    val rememberCoroutineScope = rememberCoroutineScope()
    DisposableEffect(key1 = Unit) {
        rememberCoroutineScope.launch {
            delay(2000)
            mNavController.navigate(PagesRouter.ema)
        }
        onDispose {
            rememberCoroutineScope.cancel()
        }
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(
                id = R.drawable.splash,
            ),
            contentScale = ContentScale.FillBounds,
            contentDescription = null
        )
    }
}
