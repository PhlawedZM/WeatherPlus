package com.zachm.weatherplus.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecast")
data class Forecast(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val time: String,
    val isDay: Boolean,
    val forecast: String,
    val temperature: Int,
    val precipitation: Int,
    val windSpeed: String,
    val humidity: Int,
    val pressure: Int,
    val visibility: Int,
    val migraine: String
)
