package com.zachm.weatherplus.api

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("@context")
    val context: List<Any>,
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("geometry")
    val geometry: GeometryResponse,
    @SerializedName("properties")
    val properties: Properties
)

data class GeometryResponse(
    @SerializedName("type")
    val type: String,
    @SerializedName("coordinates")
    val coordinates: List<Double>
)

data class Properties(
    @SerializedName("@id")
    val id: String,
    @SerializedName("@type")
    val type: String,
    @SerializedName("cwa")
    val cwa: String,
    @SerializedName("forecastOffice")
    val forecastOffice: String,
    @SerializedName("gridId")
    val gridId: String,
    @SerializedName("gridX")
    val gridX: Int,
    @SerializedName("gridY")
    val gridY: Int,
    @SerializedName("forecast")
    val forecast: String,
    @SerializedName("forecastHourly")
    val forecastHourly: String,
    @SerializedName("forecastGridData")
    val forecastGridData: String,
    @SerializedName("observationStations")
    val observationStations: String,
    @SerializedName("relativeLocation")
    val relativeLocation: RelativeLocation,
    @SerializedName("forecastZone")
    val forecastZone: String,
    @SerializedName("county")
    val county: String,
    @SerializedName("fireWeatherZone")
    val fireWeatherZone: String,
    @SerializedName("timeZone")
    val timeZone: String,
    @SerializedName("radarStation")
    val radarStation: String
)

data class RelativeLocation(
    @SerializedName("type")
    val type: String,
    @SerializedName("geometry")
    val geometry: GeometryResponse,
    @SerializedName("properties")
    val properties: RelativeLocationProperties
)

data class RelativeLocationProperties(
    @SerializedName("city")
    val city: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("distance")
    val distance: Distance,
    @SerializedName("bearing")
    val bearing: Bearing
)

data class Distance(
    @SerializedName("unitCode")
    val unitCode: String,
    @SerializedName("value")
    val value: Double
)

data class Bearing(
    @SerializedName("unitCode")
    val unitCode: String,
    @SerializedName("value")
    val value: Int
)