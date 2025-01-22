package com.zachm.weatherplus.util


class Tokenizer {

    var place: String? = null
    var day: String? = null
    var rain:  Boolean = false
    var snow: Boolean = false

    val location = listOf("to", "in", "at", "for", "near")
    val days = listOf("monday", "tuesday", "wednesday", "thursday", "friday", "saturday", "sunday", "today", "tomorrow", "yesterday")

    fun tokenize(input: String) {
        val tokens = mutableListOf<String>()
        input.split(" ").forEach { tokens.add(it.replace("[?./!]","")) }

        tokens.forEachIndexed { index, token ->
            when {
                location.contains(token) -> place = tokens[index+1]
                days.contains(token) -> day = token
                token == "rain" -> rain = true
                token == "snow" -> snow = true
            }
        }
    }
}
