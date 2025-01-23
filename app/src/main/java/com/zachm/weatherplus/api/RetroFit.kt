package com.zachm.weatherplus.api

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class RetroFit {
    val weatherUrl = "https://api.weather.gov/"
    val locationUrl = "https://nominatim.openstreetmap.org/"

    interface LocationApi {
        @GET("search")
        suspend fun getLocation(@retrofit2.http.Query("q") location: String, @retrofit2.http.Query("format") format: String = "json"): Response<List<LocationResponse>>
    }

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

    val apiLocation: LocationApi by lazy {
        Retrofit.Builder()
            .baseUrl(locationUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LocationApi::class.java)
    }

    suspend fun getLocation(location: String): List<LocationResponse> {
        return apiLocation.getLocation(location).body() ?: throw Exception("Failed to get location")
    }

    /**
     * Retrofit instance for the Weather API.
     */
    val apiWeather: WeatherApi by lazy {
        Retrofit.Builder()
            .baseUrl(weatherUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    /**
     * Gets the links to important weather data.
     */
    suspend fun getWeatherResponse(latitude: String, longitude: String): WeatherResponse {
        return apiWeather.getWeatherResponse(latitude, longitude).body() ?: throw Exception("Failed to get weather")
    }

    /**
     * Gets the weather from the api.
     */
    suspend fun getWeather(url: String): Weather {
        url.replace(weatherUrl, "")
        return apiWeather.getWeather(url).body() ?: throw Exception("Failed to get weather")
    }

    /**
     * Gets the stations from the api.
     */
    suspend fun getStations(url: String): Stations {
        url.replace(weatherUrl, "")
        return apiWeather.getStations(url).body() ?: throw Exception("Failed to get stations")
    }

    /**
     * Gets the observation from the api. Requires a station link.
     */
    suspend fun getObservation(url: String): Observation {
        url.replace(weatherUrl, "")
        return apiWeather.getObservation("$url/observations").body() ?: throw Exception("Failed to get observation")
    }
}