package com.example.speedtrack.composables

import android.content.Context
import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.speedtrack.components.SpeedtrackStatsPanel
import com.example.speedtrack.viewModel.MapViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapUI(mapViewModel: MapViewModel,context: Context) {

    var isMapLoaded by remember { mutableStateOf(false) }
    val userLocation by mapViewModel.userLocation.observeAsState()

    val cameraPositionState = rememberCameraPositionState()

    LaunchedEffect(userLocation) {
        userLocation?.let {
            cameraPositionState.position = CameraPosition.fromLatLngZoom(it, 15f)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.padding()
        ) {
            GoogleMap(
                modifier = Modifier
                    .size(width = 400.dp, height = 320.dp)
                    .clip(RoundedCornerShape(8.dp)),
                cameraPositionState = cameraPositionState,
                onMapLoaded = { isMapLoaded = true },
            ) {
                userLocation?.let {
                    Marker(
                        state = MarkerState(it),
                        title = "Your Location"
                    )
                }
            }
        }
        SpeedtrackStatsPanel(mapViewModel,context)
    }
}

//fun getDistance(
//    startLat: Double,
//    startLang: Double,
//    endLat: Double,
//    endLang: Double
//): Float {
//    val locStart = Location("")
//    locStart.latitude = startLat
//    locStart.longitude = startLang
//    val locEnd = Location("")
//    locEnd.latitude = endLat
//    locEnd.longitude = endLang
//    return locStart.distanceTo(locEnd)
//}







