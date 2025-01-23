package com.zachm.weatherplus.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zachm.weatherplus.HomeViewModel
import com.zachm.weatherplus.ui.theme.storm
import com.zachm.weatherplus.ui.widgets.WeatherChart
import com.zachm.weatherplus.util.ScreenDimensions

@Composable
fun TopGradientBox(onQuestionClicked: () -> Unit, onSettingsClicked: () -> Unit, colors: Pair<Color, Color>, forecast: String? ,viewModel: HomeViewModel) {
    val dimensions = ScreenDimensions().instance()

    val time by viewModel.time.collectAsState()
    val temp by viewModel.temp.collectAsState()
    val weatherResponse by viewModel.weatherResponse.collectAsState()
    var isSearch by remember {mutableStateOf(false)}

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

        if(isSearch){
            SearchTextField(
                onSearchClicked = {
                    if(it.isNotBlank()) { viewModel.fetchLocation(it) }
                    isSearch = false
                }
            )
        }

        if(!isSearch) {
            IconRow(
                onLocationClicked = {isSearch = true},
                onQuestionClicked = onQuestionClicked,
                onSettingsClicked = onSettingsClicked
            )
        }

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

@Preview
@Composable
private fun preview() {
    TopGradientBox(
        onQuestionClicked = {},
        onSettingsClicked = { },
        colors = storm,
        forecast = "Clear",
        viewModel = HomeViewModel()
    )
}