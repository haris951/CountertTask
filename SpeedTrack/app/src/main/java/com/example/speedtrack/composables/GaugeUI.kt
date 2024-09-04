package com.example.speedtrack.composables

import android.Manifest
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedtrack.components.SpeedtrackStatsPanel
import com.example.speedtrack.viewModel.MapViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun GaugeUI(mapViewModel: MapViewModel,context: Context) {
    val locationPermissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

//     Check and request permission
    LaunchedEffect(locationPermissionState) {
        if (locationPermissionState.status.isGranted) {
            mapViewModel.fetchLastKnownLocation()
        } else {
            locationPermissionState.launchPermissionRequest()
        }
    }

    val speed by mapViewModel.userSpeed.observeAsState(0f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A))

    ) {
        Box(
            modifier = Modifier
                .size(300.dp)
                .padding(top = 25.dp)
                .background(Color.Black, shape = CircleShape)
                .align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = String.format("%.2f",speed),
                color = Color.Cyan,
                fontSize = 80.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "km/h",
                color = Color.Cyan,
                fontSize = 25.sp,
                modifier = Modifier.padding(top = 100.dp)
            )
        }
        SpeedtrackStatsPanel(mapViewModel,context)
    }
}
