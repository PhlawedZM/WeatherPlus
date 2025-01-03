package com.zachm.weatherplus.api

import androidx.compose.ui.graphics.Path
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class RetroFit {
    val baseUrl = "https://api.weather.gov/"

    /**
     * Interface for the Weather API. Forecast is used and not Current Weather.
     */
    interface WeatherApi {
        @GET("points/{latitude},{longitude}")
        suspend fun getWeatherResponse(@retrofit2.http.Path("latitude") latitude: String, @retrofit2.http.Path("longitude") longitude: String): Response<WeatherResponse>

        @GET
        suspend fun getWeather(@retrofit2.http.Url url: String): Response<Weather>

        @GET
        suspend fun getStations(@retrofit2.http.Url url: String): Response<Stations>

        @GET
        suspend fun getObservation(@retrofit2.http.Url url: String): Response<Observation>
    }

    /**
     * Retrofit instance for the Weather API.
     */
    val api: WeatherApi by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    /**
     * Gets the links to important weather data.
     */
    suspend fun getWeatherResponse(latitude: String, longitude: String): WeatherResponse {
        return api.getWeatherResponse(latitude, longitude).body() ?: throw Exception("Failed to get weather")
    }

    /**
     * Gets the weather from the api.
     */
    suspend fun getWeather(url: String): Weather {
        url.replace(baseUrl, "")
        return api.getWeather(url).body() ?: throw Exception("Failed to get weather")
    }

    /**
     * Gets the stations from the api.
     */
    suspend fun getStations(url: String): Stations {
        url.replace(baseUrl, "")
        return api.getStations(url).body() ?: throw Exception("Failed to get stations")
    }

    /**
     * Gets the observation from the api. Requires a station link.
     */
    suspend fun getObservation(url: String): Observation {
        url.replace(baseUrl, "")
        return api.getObservation("$url/observations").body() ?: throw Exception("Failed to get observation")
    }
}