package com.mousavi.comosecryptocurrencyapp.presentation.list.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerAnimationView() {
    val colors = listOf(
        Color.LightGray.copy(alpha = 0.9f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.9f)
    )

    val transient = rememberInfiniteTransition()
    val animateFloat = transient.animateFloat(
        initialValue = 0f,
        targetValue = 800f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
            ),
            repeatMode = RepeatMode.Restart
        )
    )

    val brush = Brush.linearGradient(
        colors = colors,
        start = Offset(x = 10f, y = 10f),
        end = Offset(animateFloat.value, animateFloat.value)
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 24.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(100.dp)
                .background(brush)
        )

        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier
                .height(30.dp)
                .fillMaxWidth()
                .background(brush))

            Box(modifier = Modifier
                .padding(top = 10.dp)
                .height(30.dp)
                .fillMaxWidth(0.5f)
                .background(brush))
        }

    }
}
