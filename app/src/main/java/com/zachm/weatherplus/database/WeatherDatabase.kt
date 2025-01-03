package com.zachm.weatherplus.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Forecast::class], version = 1, exportSchema = false)
abstract class WeatherDatabase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDAO

    companion object {

        private var database: WeatherDatabase? = null

        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(context: Context): WeatherDatabase {
            return database ?: synchronized(this) {
                database = Room.databaseBuilder(context.applicationContext, WeatherDatabase::class.java, "weather_database").build()
                database!!
            }
        }

    }
}