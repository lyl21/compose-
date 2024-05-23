package com.mtfe.rememberwidget.cpt


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import com.mtfe.rememberwidget.common.CmPage
import com.mtfe.rememberwidget.common.CmPageBody
import com.mtfe.rememberwidget.common.CmPageHead


@Preview
@Composable
fun FadeInOutContentPreview() {
    var visible by remember { mutableStateOf(true) }
    FadeInOutContent(visible = visible) {
        Text("Your content goes here")
    }
}

@Composable
fun FadeInOutContent(
    visible: Boolean,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter =    fadeIn(animationSpec = tween(3000)),
        exit =   fadeOut(animationSpec = tween(0))
    ) {
        content()
    }
}


