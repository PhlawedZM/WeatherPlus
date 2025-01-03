package com.zachm.weatherplus.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WeatherDAO {
    @Insert
    suspend fun insert(forecasts: List<Forecast>)

    @Query("SELECT * FROM forecast")
    suspend fun getAll(): List<Forecast>

    @Query("SELECT * FROM forecast WHERE time = :time")
    suspend fun getFromHour(time: String): List<Forecast>

    @Query("DELETE FROM forecast")
    suspend fun deleteAll()
}