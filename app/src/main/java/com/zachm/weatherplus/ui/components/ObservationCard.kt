package com.zachm.weatherplus.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Card to handle weather observation stats.
 * @param modifier Modifier for the card
 * @param name Name of the observation
 * @param value Value of the observation
 * @param drawable Drawable of the observation
 */
@Composable
fun ObservationCard(modifier: Modifier, name: String, value: String, drawable: Int) {
    Card(
        modifier = modifier,
        border = BorderStroke(1.dp, Color.White.copy(alpha = 0.5f)),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Column(modifier = Modifier.padding(4.dp).fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceEvenly) {
            Text(
                text = name,
                fontWeight = FontWeight.Light,
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.8f)
            )

            Icon(
                painter = painterResource(drawable),
                contentDescription = "Humidity",
                tint = Color.White.copy(alpha = 0.8f),
                modifier = Modifier.scale(0.8f,0.8f)
            )

            Text(
                text = value,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White.copy(alpha = 0.8f)
            )
        }
    }
}