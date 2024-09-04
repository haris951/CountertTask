package com.example.speedtrack.composables

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedtrack.components.SpeedtrackStatsPanel
import com.example.speedtrack.viewModel.MapViewModel


@Composable
fun DigitalUI(mapViewModel: MapViewModel, context: Context) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A)),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(300.dp)
                .padding(top = 20.dp)
                .align(Alignment.CenterHorizontally),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "0",
                color = Color.Cyan,
                fontSize = 150.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.width(100.dp)
            )
            Text(
                text = "km/h",
                color = Color.Cyan,
                fontSize = 25.sp,
                modifier = Modifier.padding(start = 20.dp, top = 150.dp),
                fontWeight = FontWeight.Bold
            )
        }
        SpeedtrackStatsPanel(mapViewModel, context)
    }
}