package com.kotlin.spacexapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = Screen.RocketsScreen.route) {
        composable(route = Screen.RocketsScreen.route) {
            RocketsScreen()
        }
        composable(route = Screen.UpcomingLaunchesScreen.route) {
            UpcomingLaunchesScreen()
        }
        composable(route = Screen.PastLaunchesScreen.route) {
            PastLaunchesScreen()
        }
        composable(route = Screen.CompanyInfoScreen.route) {
            CompanyInfoScreen()
        }
    }
}