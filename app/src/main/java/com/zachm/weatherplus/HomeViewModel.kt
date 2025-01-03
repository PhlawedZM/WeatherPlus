package com.zachm.weatherplus

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.zachm.weatherplus.api.Observation
import com.zachm.weatherplus.api.ObservationFeature
import com.zachm.weatherplus.api.Period
import com.zachm.weatherplus.api.RetroFit
import com.zachm.weatherplus.api.Stations
import com.zachm.weatherplus.api.Weather
import com.zachm.weatherplus.api.WeatherResponse
import com.zachm.weatherplus.database.Forecast
import com.zachm.weatherplus.database.WeatherDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import java.time.Duration
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.abs
import kotlin.math.max

/**
 * ViewModel for the Home Screen. Handles weather properties.
 *
 * by Zachary Martinson * 2024
 */

class HomeViewModel : ViewModel() {

    //Live Data for Activity
    val permissionGranted: MutableLiveData<Boolean> by lazy {MutableLiveData<Boolean>(false)}
    val error: MutableLiveData<String> by lazy {MutableLiveData<String>(null)}
    val loading: MutableLiveData<Boolean> by lazy {MutableLiveData<Boolean>(false)}
    val longitude: MutableLiveData<Double> by lazy {MutableLiveData<Double>(null)}
    val latitude: MutableLiveData<Double> by lazy {MutableLiveData<Double>(null)}
    val database: MutableLiveData<WeatherDatabase> by lazy {MutableLiveData<WeatherDatabase>(null)}


    //Flow for Compose
    private val _weather = MutableStateFlow<Weather?>(null)
    val weather: StateFlow<Weather?> get() = _weather

    private val _isDay = MutableStateFlow(false)
    val isDay: StateFlow<Boolean> get() = _isDay

    private val _weatherResponse = MutableStateFlow<WeatherResponse?>(null)
    val weatherResponse: StateFlow<WeatherResponse?> get() = _weatherResponse

    private val _forecast = MutableStateFlow<String?>(null)
    val forecast: StateFlow<String?> get() = _forecast

    private val _time = MutableStateFlow(mutableListOf("12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00"))
    val time: StateFlow<MutableList<String>> get() = _time

    private val _temp = MutableStateFlow(mutableListOf(32.0, 27.0, 34.0, 25.0, 26.0, 21.0, 24.0, 18.0))
    val temp: StateFlow<MutableList<Double>> get() = _temp

    private val _stations = MutableStateFlow<Stations?>(null)
    val stations: StateFlow<Stations?> get() = _stations

    private val _observation = MutableStateFlow<Observation?>(null)
    val observation: StateFlow<Observation?> get() = _observation

    private val _pressure = MutableStateFlow<Int?>(null)
    val pressure: StateFlow<Int?> get() = _pressure

    private val _humidity = MutableStateFlow<Int?>(null)
    val humidity: StateFlow<Int?> get() = _humidity

    private val _precipitation = MutableStateFlow<Int?>(null)
    val precipitation: StateFlow<Int?> get() = _precipitation

    private val _windSpeed = MutableStateFlow<String?>(null)
    val windSpeed: StateFlow<String?> get() = _windSpeed

    private val _migraine = MutableStateFlow<String?>(null)
    val migraine: StateFlow<String?> get() = _migraine

    private val _visibility = MutableStateFlow<Int?>(null)
    val visibility: StateFlow<Int?> get() = _visibility

    private val _refreshing = MutableStateFlow(false)
    val refreshing: StateFlow<Boolean> get() = _refreshing


    /**
     * Fetches weather from Weather.gov (United States Only)
     */
    fun fetchWeather() {
        if(permissionGranted.value == true) {
            loading.value = true

            viewModelScope.launch {
                withTimeout(5000) {
                    try {
                        _weatherResponse.value = RetroFit().getWeatherResponse(latitude.value.toString(), longitude.value.toString())

                        weatherResponse.value?.let {
                            _weather.value = RetroFit().getWeather(it.properties.forecastHourly)
                            _stations.value = RetroFit().getStations(it.properties.observationStations)
                        }

                        stations.value?.let {
                            _observation.value = RetroFit().getObservation(it.features[0].id)
                        }

                        Log.d("HomeViewModel", "Weather fetched successfully. ${observation.value}")

                        getUpcomingWeather()
                        getUpcomingObservation()
                        loading.value = false
                        _refreshing.value = false
                    }
                    catch (e: Exception) {
                        Log.e("HomeViewModel", "Error fetching weather.", e)
                        _refreshing.value = false
                        error.value = e.message
                        loading.value = false
                    }
                }
            }
        }
    }

    /**
     * Gets the weather from the api. Uses GPS to get the location.
     */
    @SuppressLint("MissingPermission") //IDE being static flags this incorrectly.
    fun getWeather(locationManager: FusedLocationProviderClient) {
        if (permissionGranted.value == true) { //Need to make sure we have permissions

            viewModelScope.launch {

                withTimeout(5000) {
                    _refreshing.value = true

                    locationManager.lastLocation.addOnSuccessListener {
                        longitude.value = it.longitude
                        latitude.value = it.latitude
                        fetchWeather()
                    }
                }
            }
        }
    }

    /**
     * Gets the forecast by hour.
     */
    fun getUpcomingWeather() {
        weather.value?.let {

            viewModelScope.launch {
                try {

                    //Need to clear our list every time we update it.
                    _time.value.clear()
                    _temp.value.clear()


                    //Need to get the current time as the api doesn't have it.
                    val currentTime = LocalDateTime.now()
                    val maxSize = 24


                    it.properties.periods.forEach { hour ->
                        val hourTime = OffsetDateTime.parse(hour.startTime).toLocalDateTime()

                        if (hourTime.isAfter(currentTime) && time.value.size < maxSize) {
                            _time.value.add(DateTimeFormatter.ofPattern("h a").format(hourTime)) //Used for AM PM.
                            _temp.value.add(hour.temperature.toDouble())
                        }

                        //Get the hour of the current time for current weather (Some weather apis have current weather this does not)
                        if(hourTime.hour == currentTime.hour && hourTime.dayOfYear == currentTime.dayOfYear) {
                            _time.value.add(0,"Now")
                            _temp.value.add(0, hour.temperature.toDouble())
                            _forecast.value = hour.shortForecast
                            _isDay.value = hour.isDaytime
                            _humidity.value = hour.relativeHumidity.value
                            _precipitation.value = hour.probabilityOfPrecipitation.value
                            _windSpeed.value = hour.windSpeed
                        }
                    }

                }
                catch (e: Exception) {
                    Log.e("HomeViewModel", "Error getting upcoming weather.", e)
                    error.value = e.message
                }
            }
        }
    }

    fun getMigraineStatus(list: List<ObservationFeature>, idx: Int) {

        viewModelScope.launch {
            val start = list[idx].properties.barometricPressure?.value ?: 0
            val halfDay = list[idx+12].properties.barometricPressure?.value ?: 0
            val fullDay = list[idx+24].properties.barometricPressure?.value ?: 0

            val humidityStart = list[idx].properties.relativeHumidity?.value ?: 0
            val humidityHalfDay = list[idx+12].properties.relativeHumidity?.value ?: 0
            val humidityFullDay = list[idx+24].properties.relativeHumidity?.value ?: 0


            val distance = max(abs(start - halfDay), abs(start - fullDay))
            val humidityDistance = max(abs(humidityStart.toInt() - humidityHalfDay.toInt()), abs(humidityStart.toInt() - humidityFullDay.toInt()))


            when {
                distance > 3000 && humidityDistance > 30 -> _migraine.value = "Very High"
                distance > 3000 -> _migraine.value = "High"
                distance > 2000 && humidityDistance > 30 -> _migraine.value = "High"
                distance > 2000 -> _migraine.value = "Moderate"
                distance > 1000 -> _migraine.value = "Low"
                else -> _migraine.value = "None"
            }
        }
    }


    fun getUpcomingObservation() {
        observation.value?.let {
            viewModelScope.launch {
                try {
                    val currentTime = LocalDateTime.now()
                    var idx = 0

                    it.features.forEachIndexed { index, it ->
                        val hourTime = OffsetDateTime.parse(it.properties.timestamp).toLocalDateTime()

                        if(hourTime.hour == currentTime.hour && hourTime.dayOfYear == currentTime.dayOfYear) {
                            _pressure.value = it.properties.barometricPressure?.value ?: 0
                            _visibility.value = it.properties.visibility?.value ?: 0
                            idx = index
                        }
                    }

                    if(idx + 24 < it.features.size) {
                        getMigraineStatus(it.features, idx)
                    }
                }
                catch (e: Exception) {
                    Log.e("HomeViewModel", "Error getting upcoming weather observation.", e)
                    error.value = e.message
                }
            }
        }
    }
}