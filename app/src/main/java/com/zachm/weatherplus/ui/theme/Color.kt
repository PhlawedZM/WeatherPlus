package com.zachm.weatherplus.ui.theme

import android.util.Log
import androidx.compose.ui.graphics.Color

val ClearDark = Color(0xFF69b4c7)
val ClearLight = Color(0xFFc5e3eb)
val clear = Pair(ClearDark, ClearLight)

val SunnyDark = Color(0xFF6aa2e6)
val SunnyLight = Color(0xFFa1cee3)
val sunny = Pair(SunnyDark, SunnyLight)

val NightDark = Color(0xFF23202b)
val NightLight = Color(0xFF444a87)
val night = Pair(NightDark, NightLight)

val StormDark = Color(0xFF7a879e)
val StormLight = Color(0xFFd3e0e8)
val storm = Pair(StormDark, StormLight)

/**
 * Returns a pair of colors based on the weather.
 */
fun getWeatherColor(weather: String, isNight: Boolean): Pair<Color, Color> {
    val weather = weather.lowercase() //Have to define a val here.
    Log.d("getWeatherColor", "Weather: $weather")
    return when {
        isNight -> night
        weather.contains("rain") || weather.contains("snow") || weather.contains("overcast") || weather.contains("fog") -> storm
        weather.contains("partly") || weather.contains("cloudy") -> clear
        weather.contains("sunny") -> sunny
        else -> clear
    }
}

