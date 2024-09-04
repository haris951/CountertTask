package com.example.speedtrack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.speedtrack.composables.SpeedtrackUI
import com.example.speedtrack.navigation.MyAppNavigation
import com.example.speedtrack.ui.theme.SpeedTrackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SpeedTrackTheme {
                MyAppNavigation()
            }
        }
    }
}
