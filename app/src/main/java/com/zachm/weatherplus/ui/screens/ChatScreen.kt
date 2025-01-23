package com.zachm.weatherplus.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.zachm.weatherplus.ChatViewModel
import com.zachm.weatherplus.HomeViewModel
import com.zachm.weatherplus.R
import com.zachm.weatherplus.ui.components.ChatMessage
import com.zachm.weatherplus.ui.components.ChatTextField
import com.zachm.weatherplus.ui.components.Message
import com.zachm.weatherplus.util.ScreenDimensions

@Preview
@Composable
private fun preview() {
    ChatScreen()
}

@Composable
fun ChatScreen() {
    val viewModel: ChatViewModel = viewModel()

    val dimension = ScreenDimensions().instance()

    val messages = remember { mutableStateListOf<Message>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = Brush.verticalGradient(
                colors = listOf(
                    Color.DarkGray,
                    Color.DarkGray
                )
            )),
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(dimension.statusBarHeight))


        Icon(
            painter = painterResource(id = R.drawable.back),
            contentDescription = "Back",
            tint = Color.White.copy(alpha = 0.8f),
            modifier = Modifier
                .padding(16.dp)
                .size(32.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            reverseLayout = true
        ) {
            items(messages.size) { index ->
                ChatMessage(messages[index])
            }
        }


        ChatTextField {
            viewModel.recognize(it)
            messages.add(0, Message(it, true))
        }


        Spacer(modifier = Modifier.height(dimension.navigationBarHeight))

    }
}