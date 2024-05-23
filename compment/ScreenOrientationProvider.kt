package com.nyvc.ledbanner.compment


import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Build
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.nyvc.ledbanner.common.CommonPage
import com.nyvc.ledbanner.common.CommonPageBody
import com.nyvc.ledbanner.common.CommonPageHead


@Preview
@Composable
fun ScreenOrientationProviderPreview() {
//    ScreenOrientationProvider()
}

@Composable
fun ScreenOrientationProvider(
    orientation: Int = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED,
    color: Color,
    children: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val configuration = LocalConfiguration.current
    val activity = (LocalContext.current as? ComponentActivity)
        ?: error("No access to activity. Use LocalActivity from androidx.activity:activity-compose")

    LaunchedEffect(configuration.orientation) {
        systemUiController.setSystemBarsColor(color)
        activity.requestedOrientation = orientation

        //横屏时状态栏隐藏并透明
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            systemUiController.isSystemBarsVisible = true   //状态栏可见
        } else {
//            systemUiController.isSystemBarsVisible = false
//            systemUiController.setSystemBarsColor(color)

//            自动隐藏状态栏，下拉出现后过一段时间自动隐藏
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                activity.window.insetsController?.apply {
                    systemBarsBehavior =
                        WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                    hide(WindowInsets.Type.systemBars())
                }
            } else {
                activity.window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            }
        }
    }

//    activity.apply {
//        requestedOrientation = orientation
////            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O && orientation == ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE) {
////                requestedOrientation =
////                    ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
////            }
//
//        //横屏时状态栏隐藏并透明
//        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            systemUiController.isSystemBarsVisible = true
//        }   //状态栏可见
//    } else {
//        //systemUiController.isSystemBarsVisible = false
//        //systemUiController.setSystemBarsColor(Color.Transparent)
//
//        //自动隐藏状态栏，下拉出现后过一段时间自动隐藏
//        activity.window.insetsController?.apply {
//            systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
//            hide(WindowInsets.Type.systemBars())
//        }
//
//    }
//}
    children()
}

