package com.zachm.weatherplus

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.zachm.weatherplus.ui.screens.ChatScreen
import com.zachm.weatherplus.ui.screens.HomeScreen
import com.zachm.weatherplus.ui.theme.WeatherPlusTheme

class ChatActivity : ComponentActivity() {
    private val viewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getClient()

        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT))

        setContent {
            WeatherPlusTheme {
                ChatScreen()
            }
        }
    }
}