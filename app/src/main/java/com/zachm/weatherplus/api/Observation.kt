package com.zachm.weatherplus.api

import com.google.gson.annotations.SerializedName

data class Observation(
    @SerializedName("@context")
    val context: List<Any>,
    @SerializedName("type")
    val type: String,
    @SerializedName("features")
    val features: List<ObservationFeature>
)

data class ObservationFeature(
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("geometry")
    val geometry: ObservationGeometry,
    @SerializedName("properties")
    val properties: ObservationProperties
)

data class ObservationGeometry(
    @SerializedName("type")
    val type: String,
    @SerializedName("coordinates")
    val coordinates: List<Double>
)

data class ObservationProperties(
    @SerializedName("@id")
    val id: String,
    @SerializedName("@type")
    val type: String,
    @SerializedName("elevation")
    val elevation: ObservationElevation,
    @SerializedName("station")
    val station: String,
    @SerializedName("timestamp")
    val timestamp: String,
    @SerializedName("rawMessage")
    val rawMessage: String,
    @SerializedName("textDescription")
    val textDescription: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("presentWeather")
    val presentWeather: List<Any>,
    @SerializedName("temperature")
    val temperature: ObservationTemperature?,
    @SerializedName("dewpoint")
    val dewpoint: ObservationDewpoint?,
    @SerializedName("windDirection")
    val windDirection: WindDirection?,
    @SerializedName("windSpeed")
    val windSpeed: WindSpeed?,
    @SerializedName("windGust")
    val windGust: WindGust?,
    @SerializedName("barometricPressure")
    val barometricPressure: BarometricPressure?,
    @SerializedName("seaLevelPressure")
    val seaLevelPressure: SeaLevelPressure?,
    @SerializedName("visibility")
    val visibility: Visibility?,
    @SerializedName("maxTemperatureLast24Hours")
    val maxTemperatureLast24Hours: MaxTemperatureLast24Hours?,
    @SerializedName("minTemperatureLast24Hours")
    val minTemperatureLast24Hours: MinTemperatureLast24Hours?,
    @SerializedName("precipitationLastHour")
    val precipitationLastHour: PrecipitationLastHour?,
    @SerializedName("precipitationLast3Hours")
    val precipitationLast3Hours: PrecipitationLast3Hours?,
    @SerializedName("precipitationLast6Hours")
    val precipitationLast6Hours: PrecipitationLast6Hours?,
    @SerializedName("relativeHumidity")
    val relativeHumidity: ObservationRelativeHumidity?,
    @SerializedName("windChill")
    val windChill: WindChill?,
    @SerializedName("heatIndex")
    val heatIndex: HeatIndex?,
    @SerializedName("cloudLayers")
    val cloudLayers: List<CloudLayer>?
)

data class ObservationElevation(
    @SerializedName("unitCode")
    val unitCode: String,
    @SerializedName("value")
    val value: Int
)

data class ObservationTemperature(
    @SerializedName("unitCode")
    val unitCode: String,
    @SerializedName("value")
    val value: Double,
    @SerializedName("qualityControl")
    val qualityControl: String
)

data class ObservationDewpoint(
    @SerializedName("unitCode")
    val unitCode: String,
    @SerializedName("value")
    val value: Double,
    @SerializedName("qualityControl")
    val qualityControl: String
)

data class WindDirection(
    @SerializedName("unitCode")
    val unitCode: String,
    @SerializedName("value")
    val value: Int?,
    @SerializedName("qualityControl")
    val qualityControl: String
)

data class WindSpeed(
    @SerializedName("unitCode")
    val unitCode: String,
    @SerializedName("value")
    val value: Double,
    @SerializedName("qualityControl")
    val qualityControl: String
)

data class WindGust(
    @SerializedName("unitCode")
    val unitCode: String,
    @SerializedName("value")
    val value: Double?,
    @SerializedName("qualityControl")
    val qualityControl: String
)

data class BarometricPressure(
    @SerializedName("unitCode")
    val unitCode: String,
    @SerializedName("value")
    val value: Int,
    @SerializedName("qualityControl")
    val qualityControl: String
)

data class SeaLevelPressure(
    @SerializedName("unitCode")
    val unitCode: String,
    @SerializedName("value")
    val value: Int,
    @SerializedName("qualityControl")
    val qualityControl: String
)

data class Visibility(
    @SerializedName("unitCode")
    val unitCode: String,
    @SerializedName("value")
    val value: Int,
    @SerializedName("qualityControl")
    val qualityControl: String
)

data class MaxTemperatureLast24Hours(
    @SerializedName("unitCode")
    val unitCode: String,
    @SerializedName("value")
    val value: Double?
)

data class MinTemperatureLast24Hours(
    @SerializedName("unitCode")
    val unitCode: String,
    @SerializedName("value")
    val value: Double?
)

data class PrecipitationLastHour(
    @SerializedName("unitCode")
    val unitCode: String,
    @SerializedName("value")
    val value: Double?,
    @SerializedName("qualityControl")
    val qualityControl: String
)

data class PrecipitationLast3Hours(
    @SerializedName("unitCode")
    val unitCode: String,
    @SerializedName("value")
    val value: Double?,
    @SerializedName("qualityControl")
    val qualityControl: String
)

data class PrecipitationLast6Hours(
    @SerializedName("unitCode")
    val unitCode: String,
    @SerializedName("value")
    val value: Double?,
    @SerializedName("qualityControl")
    val qualityControl: String
)

data class ObservationRelativeHumidity(
    @SerializedName("unitCode")
    val unitCode: String,
    @SerializedName("value")
    val value: Double,
    @SerializedName("qualityControl")
    val qualityControl: String
)

data class WindChill(
    @SerializedName("unitCode")
    val unitCode: String,
    @SerializedName("value")
    val value: Double?,
    @SerializedName("qualityControl")
    val qualityControl: String
)

data class HeatIndex(
    @SerializedName("unitCode")
    val unitCode: String,
    @SerializedName("value")
    val value: Double?,
    @SerializedName("qualityControl")
    val qualityControl: String
)

data class CloudLayer(
    @SerializedName("base")
    val base: CloudBase,
    @SerializedName("amount")
    val amount: String
)

data class CloudBase(
    @SerializedName("unitCode")
    val unitCode: String,
    @SerializedName("value")
    val value: Int?
)