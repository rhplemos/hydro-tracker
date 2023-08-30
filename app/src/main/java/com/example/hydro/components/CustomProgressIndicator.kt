package com.example.hydro.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hydro.ui.theme.HydroTheme

private val cardWith = 55.dp

@Composable
fun CustomProgressIndicator(
    color: Color,
    progress: Float,
) {
    HydroTheme {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(400.dp)
        ) {
            CircularProgressIndicator(
                color = color,
                progress = progress,
                modifier = Modifier.then(Modifier.size(300.dp)),
                strokeWidth = 40.dp
            )
            Text(
                text = "${(progress * 100).toInt()}%",
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IndicatorPreview() {
    HydroTheme {
        CustomProgressIndicator(Color.Blue, 0.5f)
    }
}