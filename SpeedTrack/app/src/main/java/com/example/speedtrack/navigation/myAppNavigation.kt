package com.example.speedtrack.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.speedtrack.Routes
import com.example.speedtrack.composables.SettingUI
import com.example.speedtrack.composables.SpeedtrackUI

@Composable
fun MyAppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.speedtrackUI, builder =  {
        composable(Routes.speedtrackUI){
            SpeedtrackUI(navController)
        }
        composable(Routes.settingsUI){
            SettingUI(navController)
        }
    })

}