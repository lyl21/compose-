package com.jyrpot.gshdwr

import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationInstance
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.drawscope.ContentDrawScope



/*
* Compose 去除点击水波纹阴影效果
* */
object NoIndication: Indication {
    private object NoIndicationInstance: IndicationInstance {
        override fun ContentDrawScope.drawIndication() {
            drawContent()
        }
    }
    @Composable
    override fun rememberUpdatedInstance(interactionSource: InteractionSource): IndicationInstance {
        return NoIndicationInstance
    }
}

MaterialTheme(
colorScheme = colorScheme,
typography = Typography,
content = {
    CompositionLocalProvider(LocalIndication provides NoIndication) {
        content()
    }
}
)