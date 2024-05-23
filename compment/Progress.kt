package com.nyvc.ledbanner.compment


import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nyvc.ledbanner.common.CommonPage
import com.nyvc.ledbanner.common.CommonPageBody
import com.nyvc.ledbanner.common.CommonPageHead
import kotlin.random.Random
import kotlin.random.nextInt


@Preview
@Composable
fun ProgressPreview() {
    val sweepState = remember {
        mutableFloatStateOf(0f)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProgressBarView(sweepState)
    }
}

@Composable
fun ProgressBarView(
    sweepState: MutableState<Float>,
    modifier: Modifier = Modifier.size(300.dp),
    innerStrokeWidth:Float=2f,
    outStrokeWidth:Float=4f,
    fontSize:Int=26,
    fontColor:Color=Color.Black
) {
    val animAngle = animateFloatAsState(
        targetValue = sweepState.value/100f * 360,
        animationSpec = tween(1000)
    )
    val animPercent = animateIntAsState(
        targetValue = (sweepState.value/100f * 100).toInt(),
        animationSpec = tween(1000)
    )
    val textPercent = "${animPercent.value} %"
    val textPercentLayResult = rememberTextMeasurer().measure(
        text = AnnotatedString(textPercent),
        style = TextStyle(
            color = fontColor,
            fontSize = fontSize.sp,
            fontWeight = FontWeight.Bold
        )
    )
    Canvas(
        modifier = modifier,
        onDraw = {
        val innerStrokeWidth = innerStrokeWidth.dp.toPx()
        val radius = ((size.width)/5.5).dp.toPx()
        val outStrokeWidth = outStrokeWidth.dp.toPx()
        val canvasWidth = size.width
        val canvasHeight = size.height
        //内部圆
        drawCircle(
            Color(222, 228, 246),
            radius = radius,
            center = Offset(canvasWidth / 2, canvasHeight / 2),
            style = Stroke(innerStrokeWidth)
        )
        //圆弧进度
        drawArc(
            Color(46, 120, 249),
            startAngle = -90f,
            sweepAngle = animAngle.value,
            useCenter = false,
            size = Size(radius * 2, radius * 2),
            style = Stroke(outStrokeWidth, cap = StrokeCap.Round),
            topLeft = Offset(center.x - radius, center.y - radius)
        )
        val textPercentWidth = textPercentLayResult.size.width
        val textPercentHeight = textPercentLayResult.size.height
        drawText(
            textLayoutResult = textPercentLayResult,
            topLeft = Offset(
                canvasWidth / 2 - textPercentWidth / 2,
                canvasHeight / 2 - textPercentHeight / 2
            ),
        )
    })

}

