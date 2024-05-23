package com.jrrzx.emergencyhelper.compment


import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jrrzx.emergencyhelper.R
import com.jrrzx.emergencyhelper.ui.theme.Black20
import com.jrrzx.emergencyhelper.ui.theme.Red255
import com.jrrzx.emergencyhelper.ui.theme.White160
import kotlinx.coroutines.flow.collectLatest







@Preview
@Composable
fun SwipeBoxPreview() {
    var checked by remember { mutableStateOf(false) }
    SwipeBox(
        checked = checked,
        onCheckedChange = { checked = it },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        rtContent = {
                Box(
                    modifier = Modifier
                        .width(70.dp)
                        .fillMaxHeight()
                        .background(Color.Red)
                ) {
                    Text(
                        text = "删除",
                        modifier = Modifier.align(Alignment.Center),
                        style = TextStyle.Default.copy(
                            color = colorResource(R.color.white),
                            fontSize = 12.sp
                        )
                    )
                }
        },
        content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(R.color.white))
                        .padding(20.dp, 10.dp)
                ) {
                    Text(
                        text = "小美",
                        color = Black20,
                        fontSize = 14.sp
                    )
                    Spacer(Modifier.size(5.dp))
                    Text(
                        text = "我的电脑坏了，你能过来看看嘛。",
                        color = Black20,
                        fontSize = 12.sp
                    )
                }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwipeBox(
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?,
    modifier: Modifier = Modifier,
    rtContent: @Composable BoxScope.() -> Unit,
    content: @Composable BoxScope.() -> Unit
) {
    var bottomWidth by remember { mutableFloatStateOf(0f) }
    var bottomHeight by remember { mutableIntStateOf(0) }
    val minBound = 0f
    val maxBound = -bottomWidth
    var forceAnimationCheck by remember { mutableStateOf(false) }
    val anchoredDraggableState = remember(maxBound) {
        AnchoredDraggableState(
            initialValue = checked,
            animationSpec = TweenSpec(durationMillis = 1000),
            anchors = DraggableAnchors {
                false at minBound
                true at maxBound
            },
            positionalThreshold = { distance -> distance * 0.5f },
            velocityThreshold = { maxBound }
        )
    }
    val currentOnCheckedChange by rememberUpdatedState(onCheckedChange)
    val currentChecked by rememberUpdatedState(checked)
    LaunchedEffect(anchoredDraggableState) {
        snapshotFlow { anchoredDraggableState.currentValue }
            .collectLatest { newValue ->
                if (currentChecked != newValue) {
                    currentOnCheckedChange?.invoke(newValue)
                    forceAnimationCheck = !forceAnimationCheck
                }
            }
    }
    LaunchedEffect(checked, forceAnimationCheck) {
        if (checked != anchoredDraggableState.currentValue) {
            anchoredDraggableState.animateTo(checked)
        }
    }

    Box(
        modifier = modifier
            .anchoredDraggable(
                state = anchoredDraggableState,
                orientation = Orientation.Horizontal,
                enabled = onCheckedChange != null
            )
            .clipToBounds()
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .height(with(LocalDensity.current) {
                    bottomHeight.toDp()
                })
                .onSizeChanged {
                    bottomWidth = it.width.toFloat()
                }
        ) {
            rtContent()
        }
        Box(
            modifier = Modifier
                .graphicsLayer {
                    translationX = anchoredDraggableState.offset
                }
                .onSizeChanged {
                    bottomHeight = it.height
                }
        ) {
            content()
        }
    }
}
