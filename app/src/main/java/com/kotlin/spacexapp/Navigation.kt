package com.kotlin.spacexapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.RocketsScreen.route) {
        composable(route = Screen.RocketsScreen.route) {
            RocketsScreen()
        }
        composable(route = Screen.UpcomingLaunchesScreen.route) {
            // Upcoming launches screen
        }
    }
}