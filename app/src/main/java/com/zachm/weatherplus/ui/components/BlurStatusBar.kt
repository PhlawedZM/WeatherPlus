package com.zachm.weatherplus.ui.components

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.unit.dp
import com.zachm.weatherplus.ui.theme.ClearDark
import com.zachm.weatherplus.ui.theme.ClearLight
import com.zachm.weatherplus.util.ScreenDimensions

@Composable
fun BlurStatusBar(scrollState: ScrollState, color: Pair<Color, Color>) {

    val screenDimensions = ScreenDimensions().instance()

    val colorLerp = lerp(
        start = color.first,
        stop = color.second,
        fraction = (scrollState.value.toFloat() / (scrollState.maxValue)).coerceIn(0f, 1f)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(screenDimensions.statusBarHeight + screenDimensions.statusBarHeight/2)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colorLerp,
                        Color.Transparent
                    ),
                    startY = screenDimensions.statusBarHeight.value * 2
                )
            )
            .blur(10.dp)
    )
}