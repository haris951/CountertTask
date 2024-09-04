package com.example.speedtrack.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedtrack.R
import com.example.speedtrack.Routes
import com.example.speedtrack.viewModel.MapViewModel

@Composable
fun SpeedtrackUI(navController: NavController) {

    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf("Gauge", "Digital", "Map")

    val mapViewModel:MapViewModel = viewModel()
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1A1A1A))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
        ) {
            Text(
                text = "Speedtrack",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier.padding(start = 15.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Icon(
                    modifier = Modifier.padding(start = 100.dp),
                    painter = painterResource(id = R.drawable.baseline_access_time_24),
                    contentDescription = "",
                    tint = Color.White
                )
                Icon(
                    painter = painterResource(id = R.drawable.outline_screen_rotation_24),
                    contentDescription = "",
                    tint = Color.White
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_settings_24),
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier.clickable {
                        navController.navigate(Routes.settingsUI)
                    }
                )
            }
        }
        Row {
            Text(
                modifier = Modifier.padding(top = 8.dp, start = 15.dp),
                text = "GPS:No (0/0 Satellites)",
                color = Color.Gray,
                fontSize = 14.sp
            )
        }
        TabRow(
            modifier = Modifier.padding(12.dp),
            selectedTabIndex = selectedTabIndex,
            containerColor = Color(0xFF1A1A1A),
            contentColor = Color.Cyan
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = {
                        Text(
                            text = title,
                            color = if (selectedTabIndex == index) Color.Cyan else Color.Gray,
                            fontWeight = if (selectedTabIndex == index) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 18.sp
                        )
                    }
                )
            }
        }

        when (selectedTabIndex) {
            0 -> GaugeUI(mapViewModel,context)
            1 -> DigitalUI(mapViewModel,context)
            2 -> MapUI(mapViewModel, context)
        }
    }
}



