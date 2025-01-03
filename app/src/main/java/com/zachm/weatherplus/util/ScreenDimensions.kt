package com.zachm.weatherplus.util

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class ScreenDimensions {

    data class ScreenDimension(val statusBarHeight: Dp, val navigationBarHeight: Dp, val screenHeightE2E: Dp, val screenHeight: Dp)

    /**
     * Gets the screen dimensions.
     */
    @Composable
    fun instance(): ScreenDimension {
        val density = LocalDensity.current
        val config = LocalConfiguration.current

        val insets = WindowInsets.systemBars.asPaddingValues()
        val statusBarHeight = with(density) { insets.calculateTopPadding().toPx().toDp() }
        val navigationBarHeight = with(density) { insets.calculateBottomPadding().toPx().toDp() }

        val screenHeightE2E = config.screenHeightDp.dp + statusBarHeight + navigationBarHeight
        val screenHeight = config.screenHeightDp.dp

        return ScreenDimension(statusBarHeight, navigationBarHeight, screenHeightE2E, screenHeight)
    }
}