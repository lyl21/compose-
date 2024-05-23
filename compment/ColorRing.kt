package com.nyvc.ledbanner.compment


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColor
import com.nyvc.ledbanner.common.CommonPage
import com.nyvc.ledbanner.common.CommonPageBody
import com.nyvc.ledbanner.common.CommonPageHead


@Preview
@Composable
fun ColorRingPreview() {
    ColorRing()
}

@Composable
fun ColorRing(
    sizeDP: Dp = 100.dp,
    strokeWidth:Float=10f,
    innerColor: Color = Color.LightGray,
    colors: List<Color> = listOf(
        Color(android.graphics.Color.parseColor("#dd2c00")),
        Color(android.graphics.Color.parseColor("#d50000")),
        Color(android.graphics.Color.parseColor("#c51162")),
        Color(android.graphics.Color.parseColor("#aa00ff")),
        Color(android.graphics.Color.parseColor("#6200ea")),
        Color(android.graphics.Color.parseColor("#304ffe")),
        Color(android.graphics.Color.parseColor("#2962ff")),
        Color(android.graphics.Color.parseColor("#0091ea")),
        Color(android.graphics.Color.parseColor("#00b8d4")),
        Color(android.graphics.Color.parseColor("#00bfa5")),
        Color(android.graphics.Color.parseColor("#00c853")),
        Color(android.graphics.Color.parseColor("#64dd17")),
        Color(android.graphics.Color.parseColor("#aeea00")),
        Color(android.graphics.Color.parseColor("#ffd600")),
        Color(android.graphics.Color.parseColor("#ffab00")),
    ),
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.size(sizeDP)) {
        val center = Offset(size.width / 2, size.height / 2)
        val radius = size.minDimension / 2 - strokeWidth *2
        drawCircle(
            color = innerColor,
            center = center,
            radius = radius
        )
        val gradientBrush = Brush.sweepGradient(
            colors = colors,
            center = center,
        )
        drawArc(
            brush = gradientBrush,
            startAngle = 0f,
            sweepAngle = 360f,
            useCenter = false,
            style = Stroke(width = strokeWidth)
        )
    }
}


