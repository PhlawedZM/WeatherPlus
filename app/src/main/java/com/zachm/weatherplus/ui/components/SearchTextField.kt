package com.zachm.weatherplus.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.zachm.weatherplus.R

@Composable
fun SearchTextField(onSearchClicked: (String) -> Unit) {

    var input by remember { mutableStateOf("") }
    var image by remember { mutableIntStateOf(R.drawable.exit) }

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .border(BorderStroke(1.dp, Color.White.copy(alpha = 0.8f)), RoundedCornerShape(12.dp)),
        trailingIcon = {
            Icon(
                modifier = Modifier
                    .size(22.dp)
                    .clickable { onSearchClicked(input) },
                painter = painterResource(id = image),
                contentDescription = "Search",
                tint = Color.White.copy(alpha = 0.8f),
            )
        },
        singleLine = true,
        value = input,
        onValueChange = {
            input = it
            image = when(it.count()) {
                0 -> R.drawable.exit
                else -> R.drawable.search
            }
        },
        placeholder = { Text(text = "Search", color = Color.White.copy(alpha = 0.5f)) },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.Transparent,
            unfocusedTextColor = Color.White.copy(alpha = 0.8f),
            focusedContainerColor = Color.Transparent,
            focusedTextColor = Color.White.copy(alpha = 0.8f),
            unfocusedTrailingIconColor = Color.White.copy(alpha = 0.8f),
            focusedTrailingIconColor = Color.White.copy(alpha = 0.8f),
            cursorColor = Color.White.copy(alpha = 0.8f),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchClicked(input)
            }
        )
    )
}