package com.zachm.weatherplus.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zachm.weatherplus.HomeViewModel
import com.zachm.weatherplus.api.Weather
import com.zachm.weatherplus.api.WeatherResponse
import com.zachm.weatherplus.ui.theme.ClearDark
import com.zachm.weatherplus.ui.theme.ClearLight
import com.zachm.weatherplus.ui.theme.NightDark
import com.zachm.weatherplus.ui.theme.NightLight
import com.zachm.weatherplus.ui.theme.StormDark
import com.zachm.weatherplus.ui.theme.StormLight
import com.zachm.weatherplus.ui.theme.SunnyDark
import com.zachm.weatherplus.ui.theme.SunnyLight
import com.zachm.weatherplus.ui.widgets.WeatherChart
import com.zachm.weatherplus.util.ScreenDimensions

@Composable
fun TopGradientBox(onLocationClicked: () -> Unit, onQuestionClicked: () -> Unit, onSettingsClicked: () -> Unit, weather: Weather?, weatherResponse: WeatherResponse?, colors: Pair<Color, Color>, forecast: String? ,viewModel: HomeViewModel) {
    val dimensions = ScreenDimensions().instance()

    val time by viewModel.time.collectAsState()
    val temp by viewModel.temp.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensions.screenHeightE2E)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colors.first,
                        colors.second
                    )
                )
            )
    ) {
        Spacer(modifier = Modifier.height(dimensions.statusBarHeight))

        IconRow(
            onLocationClicked = onLocationClicked,
            onQuestionClicked = onQuestionClicked,
            onSettingsClicked = onSettingsClicked
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = forecast ?: "Sunny",
            fontSize = 20.sp,
            color = Color.White,
            fontWeight = FontWeight.Light,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(
                text = " ${temp[0].toInt()}°",
                fontSize = 140.sp,
                color = Color.White,
                fontWeight = FontWeight.Light
            )
        }

        Text(
            text = "${weatherResponse?.properties?.relativeLocation?.properties?.city ?: "New York"}, ${weatherResponse?.properties?.relativeLocation?.properties?.state ?: "NY"}",
            fontSize = 20.sp,
            color = Color.White,
            fontWeight = FontWeight.Light,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.weight(1f))

        WeatherChart(
            modifier = Modifier
                .height(200.dp)
                .width(1800.dp),
            time, temp
        )

        Spacer(modifier = Modifier.height(dimensions.navigationBarHeight))
    }
}