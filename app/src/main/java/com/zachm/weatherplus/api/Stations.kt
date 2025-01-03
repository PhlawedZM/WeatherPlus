package com.zachm.weatherplus.api

import com.google.gson.annotations.SerializedName

data class Stations(
    @SerializedName("@context")
    val context: List<Any>,
    @SerializedName("type")
    val type: String,
    @SerializedName("features")
    val features: List<StationFeature>
)

data class StationFeature(
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("geometry")
    val geometry: StationGeometry,
    @SerializedName("properties")
    val properties: StationProperties
)

data class StationGeometry(
    @SerializedName("type")
    val type: String,
    @SerializedName("coordinates")
    val coordinates: List<Double>
)

data class StationProperties(
    @SerializedName("@id")
    val id: String,
    @SerializedName("@type")
    val type: String,
    @SerializedName("elevation")
    val elevation: StationElevation,
    @SerializedName("stationIdentifier")
    val stationIdentifier: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("timeZone")
    val timeZone: String,
    @SerializedName("forecast")
    val forecast: String,
    @SerializedName("county")
    val county: String,
    @SerializedName("fireWeatherZone")
    val fireWeatherZone: String
)

data class StationElevation(
    @SerializedName("unitCode")
    val unitCode: String,
    @SerializedName("value")
    val value: Double
)