package com.zachm.weatherplus.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zachm.weatherplus.HomeViewModel
import com.zachm.weatherplus.ui.components.BlurStatusBar
import com.zachm.weatherplus.ui.components.BottomGradientBox
import com.zachm.weatherplus.ui.components.TopGradientBox
import com.zachm.weatherplus.ui.theme.clear
import com.zachm.weatherplus.ui.theme.getWeatherColor
import com.zachm.weatherplus.ui.widgets.ParticleEmitter
import com.zachm.weatherplus.ui.widgets.RainParticle
import com.zachm.weatherplus.ui.widgets.SnowParticle

@Preview
@Composable
private fun preview() {
    HomeScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = viewModel()

    val scrollState = rememberScrollState()
    val forecast by viewModel.forecast.collectAsState()
    val isDay by viewModel.isDay.collectAsState()
    val isRefreshing by viewModel.refreshing.collectAsState()
    val refreshState = rememberPullToRefreshState()

    var isSnow = forecast?.contains("Snow") ?: false
    var isRain = forecast?.contains("Rain") ?: false

    val colors = forecast?.let {getWeatherColor(it, !isDay)}

    val onRefresh: () -> Unit = {
        viewModel.permissionGranted.value = true
    }


    Box(modifier = Modifier.fillMaxSize()) {

        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
            state = refreshState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                TopGradientBox(
                    onQuestionClicked = { viewModel.switchToChat.value = true },
                    onSettingsClicked = { },
                    forecast = forecast,
                    colors = colors ?: clear,
                    viewModel = viewModel
                )

                BottomGradientBox(
                    colors = colors ?: clear,
                    viewModel = viewModel
                )
            }
            
        }

        BlurStatusBar(
            scrollState = scrollState,
            color = colors ?: clear
            )

        if(isSnow) {
            ParticleEmitter(
                modifier = Modifier.fillMaxSize(),
                particleAmount = 2,
                particleStartX = 0F,
                particleStartY = 0F,
                particleSize = 8F,
                particleType = SnowParticle,
                particleRandomness = 1200F)
        }
        if(isRain) {
            ParticleEmitter(
                modifier = Modifier.fillMaxSize(),
                particleAmount = 3,
                particleStartX = 0F,
                particleStartY = 0F,
                particleSize = 10F,
                particleType = RainParticle,
                particleRandomness = 1200F
            )
        }

    }
}

