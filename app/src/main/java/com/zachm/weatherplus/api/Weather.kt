package com.zachm.weatherplus.api

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("@context")
    val context: List<Any>,
    @SerializedName("type")
    val type: String,
    @SerializedName("geometry")
    val geometry: Geometry,
    @SerializedName("properties")
    val properties: HourlyForecastProperties
)

data class Geometry(
    @SerializedName("type")
    val type: String,
    @SerializedName("coordinates")
    val coordinates: List<List<List<Double>>>
)

data class HourlyForecastProperties(
    @SerializedName("units")
    val units: String,
    @SerializedName("forecastGenerator")
    val forecastGenerator: String,
    @SerializedName("generatedAt")
    val generatedAt: String,
    @SerializedName("updateTime")
    val updateTime: String,
    @SerializedName("validTimes")
    val validTimes: String,
    @SerializedName("elevation")
    val elevation: Elevation,
    @SerializedName("periods")
    val periods: List<Period>
)

data class Elevation(
    @SerializedName("unitCode")
    val unitCode: String,
    @SerializedName("value")
    val value: Double
)

data class Period(
    @SerializedName("number")
    val number: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("startTime")
    val startTime: String,
    @SerializedName("endTime")
    val endTime: String,
    @SerializedName("isDaytime")
    val isDaytime: Boolean,
    @SerializedName("temperature")
    val temperature: Int,
    @SerializedName("temperatureUnit")
    val temperatureUnit: String,
    @SerializedName("temperatureTrend")
    val temperatureTrend: String?,
    @SerializedName("probabilityOfPrecipitation")
    val probabilityOfPrecipitation: ProbabilityOfPrecipitation,
    @SerializedName("dewpoint")
    val dewpoint: Dewpoint,
    @SerializedName("relativeHumidity")
    val relativeHumidity: RelativeHumidity,
    @SerializedName("windSpeed")
    val windSpeed: String,
    @SerializedName("windDirection")
    val windDirection: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("shortForecast")
    val shortForecast: String,
    @SerializedName("detailedForecast")
    val detailedForecast: String
)

data class ProbabilityOfPrecipitation(
    @SerializedName("unitCode")
    val unitCode: String,
    @SerializedName("value")
    val value: Int?
)

data class Dewpoint(
    @SerializedName("unitCode")
    val unitCode: String,
    @SerializedName("value")
    val value: Double
)

data class RelativeHumidity(
    @SerializedName("unitCode")
    val unitCode: String,
    @SerializedName("value")
    val value: Int
)