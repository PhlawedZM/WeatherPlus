package com.zachm.weatherplus.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zachm.weatherplus.R

@Composable
fun IconRow(onLocationClicked: () -> Unit, onQuestionClicked: () -> Unit, onSettingsClicked: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(16.dp, 10.dp)
            .fillMaxWidth()
    ) {
        IconButton(
            onClick = { onLocationClicked() },
        ) {
            Icon(
                painter = painterResource(id = R.drawable.location_on),
                contentDescription = "Location",
                tint = Color.White,
                modifier = Modifier.scale(0.9f)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable { onQuestionClicked() }
        ) {
            Text(
                text = "Ask a Question?",
                color = Color.White,
                fontSize = 17.sp
            )
            Icon(
                painter = painterResource(id = R.drawable.chat),
                contentDescription = "Chat",
                tint = Color.White,
                modifier = Modifier.scale(0.7f)
            )
        }

        IconButton(
            onClick = { onSettingsClicked() }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.settings),
                contentDescription = "Settings",
                tint = Color.White,
                modifier = Modifier.scale(0.9f)
            )
        }
    }
}