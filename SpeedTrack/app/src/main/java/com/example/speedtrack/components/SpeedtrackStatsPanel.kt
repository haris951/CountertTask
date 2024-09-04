package com.example.speedtrack.components

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Location
import android.provider.Settings
import android.location.LocationManager
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedtrack.R
import com.example.speedtrack.viewModel.MapViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

@SuppressLint("DefaultLocale")
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun SpeedtrackStatsPanel(mapViewModel: MapViewModel, context: Context) {

    val userLocation by mapViewModel.userLocation.observeAsState()
    val locationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)
    var isLocationEnabled by remember { mutableStateOf(false) }
    var showLocationSettingsDialog by remember { mutableStateOf(false) }
    var timerStarted by remember { mutableStateOf(false) }
    var timerPaused by remember { mutableStateOf(false) }
    var elapsedTime by remember { mutableLongStateOf(0L) }
    var previousLocation by remember { mutableStateOf<LatLng?>(null) }
    var totalDistance by remember { mutableDoubleStateOf(0.0) }
    var maxSpeed by remember { mutableDoubleStateOf(0.0) }
    val startTime by remember { mutableDoubleStateOf(0.0) }

    var averageSpeed = if (elapsedTime > 0) {
        val distanceInKm = totalDistance / 1000 //convert distance from meters to kilometers
        val timeInHours = elapsedTime / 3600.0 // convert the time from seconds to hours
        distanceInKm / timeInHours
    } else {
        0.0
    }

    val formattedAverageSpeed = String.format("%.2f km/h", averageSpeed)
    val formattedMaxSpeed = String.format("%.2f km/h", maxSpeed)
    val formattedDistance = String.format("%.2f km", totalDistance / 1000)

    val formattedTime = String.format(
        "%02d:%02d:%02d",
        TimeUnit.SECONDS.toHours(elapsedTime),
        TimeUnit.SECONDS.toMinutes(elapsedTime) % 60,
        elapsedTime % 60
    )

    LaunchedEffect(timerStarted, timerPaused) {
        if (timerStarted && !timerPaused) {
            while (true) {
                delay(1000L)
                elapsedTime++
                userLocation?.let { currentLocation ->
                    previousLocation?.let { prevLocation ->
                        val results = FloatArray(1)
                        Location.distanceBetween(
                            prevLocation.latitude, prevLocation.longitude,
                            currentLocation.latitude, currentLocation.longitude,
                            results
                        )
                        totalDistance += results[0]

                        val timeInterval = (System.currentTimeMillis() - startTime) / 1000.0     // Time interval in seconds
                        val speed = if (timeInterval > 0) results[0] / timeInterval else 0.0    // Speed in m/s
                        val speedInKmH = speed * 3.6 // Convert to km/h

                        if (speedInKmH > maxSpeed) {
                            maxSpeed = speedInKmH
                        }
                        Log.d("Speedtrack", "Speed: $speedInKmH km/h, Max Speed: $maxSpeed km/h")

                    }
                }
            }
        }
    }

    // Check if location services are enabled
    LaunchedEffect(locationPermissionState.status.isGranted) {
        if (locationPermissionState.status.isGranted) {
            val locationManager =
                context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            isLocationEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                    locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        }
    }
    // Handle location settings dialog
    if (showLocationSettingsDialog) {
        AlertDialog(
            containerColor = Color.DarkGray,
            onDismissRequest = { showLocationSettingsDialog = false },
            title = { Text("No GPS", color = Color.White, fontWeight = FontWeight.Bold) },
            text = {
                Text(
                    "Location access denied. Please turn on Location service.",
                    color = Color.White
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showLocationSettingsDialog = false
                        context.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan)
                )
                {
                    Text("Turn on", color = Color.Black)
                }
            },
            dismissButton = {
                Button(
                    onClick = { showLocationSettingsDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                ) {
                    Text("Cancel", color = Color.White)
                }
            }
        )
    }

// ------------------------------------------------------------------------------//
    // ----------------------------------------------------------------------------//
    // -------------------------------------------------------------------------//
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
        ) {
            Column(modifier = Modifier.padding(start = 40.dp)) {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_timer_24),
                        contentDescription = null,
                        tint = Color.White
                    )
                    Text(
                        text = "Duration",
                        color = Color.White,
                        fontSize = 17.sp,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
                Text(
                    text = formattedTime,
                    color = Color.Cyan,
                    fontSize = 26.sp
                )
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(start = 70.dp)
            ) {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_route_24),
                        contentDescription = null,
                        tint = Color.White
                    )
                    Text(
                        text = "Distance",
                        color = Color.White,
                        fontSize = 17.sp,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
                Text(text = formattedDistance, color = Color.Cyan, fontSize = 26.sp)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(start = 40.dp)
            ) {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_speed_24),
                        contentDescription = null,
                        tint = Color.White
                    )
                    Text(
                        text = "Avg.Speed",
                        color = Color.White,
                        fontSize = 17.sp,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
                Text(text = formattedAverageSpeed, color = Color.Cyan, fontSize = 26.sp)
            }
            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(start = 50.dp)
            ) {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_speed_24),
                        contentDescription = null,
                        tint = Color.White
                    )
                    Text(
                        text = "Max.Speed",
                        color = Color.White,
                        fontSize = 17.sp,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
                Text(text = formattedMaxSpeed, color = Color.Cyan, fontSize = 26.sp)
            }
        }
        if (!timerStarted) {
            Button(
                modifier = Modifier
                    .width(160.dp)
                    .height(75.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 25.dp),
                onClick = {
                    if (locationPermissionState.status.isGranted) {
                        val locationManager =
                            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
                        isLocationEnabled =
                            locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                            locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
                        if (isLocationEnabled) {
//                            mapViewModel.fetchLastKnownLocation()
                            timerStarted = true
                            timerPaused = false
                            elapsedTime = 0L
                            previousLocation = null
                            totalDistance = 0.0
                            maxSpeed = 0.0
                        } else {
                            showLocationSettingsDialog = true
                        }
                    } else {
                        locationPermissionState.launchPermissionRequest()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Cyan
                )
            ) {
                Text(
                    text = "Start",
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Stop and Resume/Pause Buttons (Visible only if timer is started)
        if (timerStarted && isLocationEnabled) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 25.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Reset Button
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            timerStarted = false
                            timerPaused = false
                            elapsedTime = 0L
                            previousLocation = null
                            maxSpeed = 0.0
                            averageSpeed = 0.0
                            totalDistance = 0.0
                        },
                        shape = CircleShape,
                        modifier = Modifier
                            .width(70.dp)
                            .height(50.dp)
                            .size(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_refresh_24),
                            contentDescription = "Reset",
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                    Text(
                        text = "Reset",
                        color = Color.White,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
                // Stop Button
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = {
                            // Handle Stop
                            timerStarted = false
                        },
                        shape = CircleShape,
                        modifier = Modifier
                            .height(55.dp)
                            .width(160.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Stop",
                                color = Color.Cyan,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        }
                    }
                }
                // Pause Button
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        shape = CircleShape,
                        modifier = Modifier
                            .width(70.dp)
                            .height(50.dp)
                            .size(50.dp),
                        onClick = {
                            timerPaused = !timerPaused
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
                    ) {
                        if (timerPaused) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_play_arrow_24), // Replace with your pause icon
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(30.dp)
                            )
                        } else {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_pause_24), // Replace with your pause icon
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(30.dp)
                            )
                        }

                    }
                    Text(
                        text = if (timerPaused) "Resume" else "Pause",
                        color = Color.White,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

    }
}


