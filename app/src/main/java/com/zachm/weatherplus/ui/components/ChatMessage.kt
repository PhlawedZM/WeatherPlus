package com.zachm.weatherplus.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

data class Message(val message: String, val isUser: Boolean)

@Composable
fun ChatMessage(message: Message) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = when {
            message.isUser -> Arrangement.Absolute.Right
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
                .widthIn(max = 250.dp)
        ) {
            Text(
                text = message.message,
                color = Color.DarkGray,
                modifier = Modifier.padding(10.dp)
            )

        }
    }
}