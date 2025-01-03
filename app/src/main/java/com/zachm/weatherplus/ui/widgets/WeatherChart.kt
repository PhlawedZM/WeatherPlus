package com.zachm.weatherplus.ui.widgets

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect.Companion.dashPathEffect
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.lang.Math.round


@Composable
fun WeatherChart(modifier: Modifier, time: List<String>, temp: List<Double>) {

    val maxTemp = temp.max() + 10.0
    val minTemp = temp.min() - 5.0
    val range = maxTemp - minTemp

    val measurer = rememberTextMeasurer()
    val scrollState = rememberScrollState()

    val highlight = round(scrollState.value.toFloat() / (scrollState.maxValue/time.size)).coerceIn(0, time.size-1)


    Row(modifier = Modifier
        .horizontalScroll(scrollState)) {
        Canvas(
            modifier = modifier
        ) {
            val height = size.height
            val width = size.width

            //Padded on the sides
            val points = List(time.size) { index ->
                val distance = width / time.size
                val x = index * distance
                val y = ((maxTemp - temp[index]) / range) * height
                Offset(x + (distance / 2), y.toFloat())
            }

            val path = Path().apply {
                moveTo(points.first().x, points.first().y)

                for (point in points.drop(1)) {
                    lineTo(point.x, point.y)
                }
            }

            drawPath(path,
                color = Color.White,
                style = Stroke(
                    width = 1f,
                    pathEffect = dashPathEffect(floatArrayOf(5f, 5f), 0f))
            )

            for(point in points) {

                drawCircle(
                    color = Color.White,
                    radius = 5f,
                    center = point
                )

                drawText(
                    textMeasurer = measurer,
                    text = time[points.indexOf(point)],
                    style = TextStyle(color = Color.White, fontSize = 10.sp),
                    topLeft = Offset(point.x - 30F, height - 30.0F)
                )

                drawText(
                    textMeasurer = measurer,
                    text = "${temp[points.indexOf(point)]}°",
                    style = TextStyle(color = Color.White, fontSize = 10.sp),
                    topLeft = Offset(point.x - 30F, point.y - 60.0F)
                )

                val fadedPath = Path().apply {
                    moveTo(point.x, height - 40.0F)
                    lineTo(point.x, point.y)
                }

                drawPath(
                    path = fadedPath,
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White,
                            Color.Transparent
                        ),
                        startY = height/2
                    ),
                    style = Stroke(width = 1f)
                )
            }
            
            drawCircle(
                color = Color.White.copy(alpha = 0.5f),
                radius = 10f,
                center = points[highlight]
            )
        }
    }
}

@Preview
@Composable
fun preview() {
    val time = listOf("12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00")
    val temp = listOf(32.0, 27.0, 34.0, 25.0, 26.0, 21.0, 24.0, 18.0)

    WeatherChart(modifier = Modifier,time, temp)
}