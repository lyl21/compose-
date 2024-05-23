package com.mtfe.rememberwidget.cpt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mtfe.rememberwidget.ext.debouncedClickable


@Composable
fun BtSubmitText(text: String = "Submit", bgColor: Color= Color.Black, textColor: Color = Color.White, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        shape= RoundedCornerShape(16.dp),
        colors= ButtonDefaults.buttonColors(
            containerColor =bgColor
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(10.dp)
        )
    }
}