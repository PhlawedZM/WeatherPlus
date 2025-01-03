package com.zachm.weatherplus

import android.util.Log
import com.zachm.weatherplus.api.BarometricPressure
import com.zachm.weatherplus.api.ObservationElevation
import com.zachm.weatherplus.api.ObservationFeature
import com.zachm.weatherplus.api.ObservationGeometry
import com.zachm.weatherplus.api.ObservationProperties
import com.zachm.weatherplus.api.ObservationRelativeHumidity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


/**
 * JUNIT test to see if the migraine status is correct.
 */
class MigraineUnitTest {

    private lateinit var viewModel: HomeViewModel
    private lateinit var list: MutableList<ObservationFeature>

    @Before
    fun setUp() {
        viewModel = HomeViewModel()
        list = MutableList(30) { createObservationFeature(101000, 40.0) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun TestMigraineHigh() = runTest {
        //We replace the 0,12,24 values with our test values.
        list[24] = createObservationFeature(105000, 40.0)

        //We launch a coroutine to get the migraine status.
        launch{viewModel.getMigraineStatus(list, 0)}

        //Wait til the coroutine is done. Or else we get null
        advanceUntilIdle()

        Log.d("MigraineTest", "${viewModel.migraine.value}")
        assert(viewModel.migraine.value == "High")
    }

    /**
     * Creates a mock observation feature.
     */
    private fun createObservationFeature(pressure: Int, humidity: Double): ObservationFeature {
        return ObservationFeature(
            id = "",
            type = "Feature",
            geometry = ObservationGeometry(
                type = "",
                coordinates = listOf()
            ),
            properties = ObservationProperties(
                id = "",
                type = "",
                elevation = ObservationElevation(
                    unitCode = "",
                    value = 0
                ),
                station = "",
                timestamp = "",
                rawMessage = "",
                textDescription = "",
                icon = "",
                presentWeather = listOf(),
                temperature = null,
                dewpoint = null,
                windDirection = null,
                windSpeed = null,
                windGust = null,
                seaLevelPressure = null,
                visibility = null,
                maxTemperatureLast24Hours = null,
                minTemperatureLast24Hours = null,
                precipitationLastHour = null,
                precipitationLast3Hours = null,
                precipitationLast6Hours = null,
                windChill = null,
                heatIndex = null,
                cloudLayers = null,
                barometricPressure = BarometricPressure(
                    unitCode = "",
                    value = pressure,
                    qualityControl = ""
                ),
                relativeHumidity = ObservationRelativeHumidity(
                    unitCode = "",
                    value = humidity,
                    qualityControl = ""
                )
            )
        )
    }

}