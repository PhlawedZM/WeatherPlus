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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import com.zachm.weatherplus.R
import com.zachm.weatherplus.util.ScreenDimensions

@Preview
@Composable
private fun preview() {
    ChatScreen()
}

@Composable
fun ChatScreen() {

    val dimension = ScreenDimensions().instance()
    
    var text by remember { mutableStateOf("") }
    var messages = remember { mutableStateListOf<Message>() }

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

            }
        }


        TextField(
            value = text,
            onValueChange = { text = it },
            textStyle = TextStyle(fontSize = 16.sp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .border(
                    width = 6.dp,
                    color = Color.Gray,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(4.dp),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.Gray,
                unfocusedTextColor = Color.White,
                focusedContainerColor = Color.Gray,
                focusedTextColor = Color.White,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = Color.White
            ),
            placeholder = {Text(text = "Type a message", color = Color.White.copy(alpha = 0.5f))},
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
        )


        Spacer(modifier = Modifier.height(dimension.navigationBarHeight))

    }
}

data class Message(val message: String, val isUser: Boolean)

@Composable
fun ChatMessage(message: String, isUser: Boolean) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = when {
            isUser -> Arrangement.Absolute.Right
            else -> Arrangement.Absolute.Left
        }
    ) {

        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.LightGray
            ),
            modifier = Modifier
                .padding(20.dp)
        ) {
            Text(
                text = "How are you doing today?",
                color = Color.DarkGray,
                modifier = Modifier.padding(10.dp)
            )

        }
    }
}