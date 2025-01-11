package com.zachm.weatherplus.ui.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zachm.weatherplus.HomeViewModel
import com.zachm.weatherplus.R
import com.zachm.weatherplus.ui.theme.night
import com.zachm.weatherplus.util.ScreenDimensions

@Composable
fun BottomGradientBox(colors: Pair<Color, Color>, viewModel: HomeViewModel) {
    val dimension = ScreenDimensions().instance()

    val humidity by viewModel.humidity.collectAsState()
    val pressure by viewModel.pressure.collectAsState()
    val visibility by viewModel.visibility.collectAsState()
    val migraine by viewModel.migraine.collectAsState()
    val precipitation by viewModel.precipitation.collectAsState()
    val windSpeed by viewModel.windSpeed.collectAsState()

    val standardPressure = 101325 //Pascal average
    val miles = 1609
    val km = 1000

    Log.d("BottomGradientBox", "Humidity: $humidity, Visibility: $visibility, Pressure: $pressure")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimension.screenHeightE2E)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        colors.second,
                        colors.first
                    )
                )
            )
    ) {

        Spacer(Modifier.height(dimension.statusBarHeight)) //We need to offset for when user is fully scrolled

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly) {

            ObservationCard(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(12.dp),
                name = "Humidity",
                value = "${humidity ?: 30}%",
                drawable = R.drawable.water_drop
            )

            ObservationCard(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(12.dp),
                name = "Pressure",
                value = "${(((pressure ?: standardPressure).toDouble() / standardPressure) * 100).toInt()}%",
                drawable = R.drawable.pressure
            )

            ObservationCard(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(12.dp),
                name = "Visibility",
                value = "${((visibility ?: 10000).toDouble() / miles).let { String.format("%.2f", it) }} mi",
                drawable = R.drawable.visibility
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceEvenly) {

            ObservationCard(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(12.dp),
                name = "Precipitation",
                value = "${precipitation ?: 0}%",
                drawable = R.drawable.thunderstorm
            )

            ObservationCard(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(12.dp),
                name = "Migraine",
                value = migraine ?: "None",
                drawable = R.drawable.face
            )

            ObservationCard(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(12.dp),
                name = "Wind Speed",
                value = windSpeed ?: "0 mph",
                drawable = R.drawable.air
            )
        }
    }
}

@Preview
@Composable
private fun preview() {
    BottomGradientBox(colors = night, viewModel = HomeViewModel())
}