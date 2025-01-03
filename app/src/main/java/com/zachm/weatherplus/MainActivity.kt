package com.zachm.weatherplus

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.location.LocationServices
import com.zachm.weatherplus.database.Forecast
import com.zachm.weatherplus.database.WeatherDAO
import com.zachm.weatherplus.database.WeatherDatabase
import com.zachm.weatherplus.ui.screens.HomeScreen
import com.zachm.weatherplus.ui.theme.WeatherPlusTheme

class MainActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPermissions()

        viewModel.error.observe(this) {onError(it)}
        viewModel.permissionGranted.observe(this) {if(it) {viewModel.getWeather(LocationServices.getFusedLocationProviderClient(this))} }
        viewModel.database.value = WeatherDatabase.getInstance(this)

        //Sets the status bar and navigation bar to transparent.
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.dark(Color.TRANSPARENT),
            statusBarStyle = SystemBarStyle.dark(Color.TRANSPARENT))

        setContent {
            WeatherPlusTheme {
                HomeScreen()
            }
        }
    }

    /**
     * Gives an toast for an error.
     */
    private fun onError(error: String?) {
        error?.let { Toast.makeText(this, "Sorry we had an issue. $it", Toast.LENGTH_SHORT).show()  }
    }

    /**
     * Launches the permission dialog.
     */
    private fun requestPermissions() {
        permissionLauncher.launch(arrayOf(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ))
    }

    /**
     * Registers permissions and tracks them.
     */
    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
        val allGranted = it.all { entry -> entry.value }
        viewModel.permissionGranted.value = allGranted
        Log.d("Permissions", "All permissions granted: $allGranted")

        it.entries.forEach { entry ->
            val permission = entry.key
            val granted = entry.value

            if(granted) {
                Log.d("Permissions", "$permission granted.")
            }
            else {
                Log.d("Permissions", "$permission denied.")
            }
        }
    }
}