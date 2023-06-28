package com.kotlin.spacexapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.kotlin.spacexapp.ui.app.screens.PastLaunchesScreen
import com.kotlin.spacexapp.ui.app.screens.RocketsScreen
import com.kotlin.spacexapp.ui.app.screens.UpcomingLaunchesScreen

@Composable
fun Navigation(navHostController: NavHostController, mainViewModel: MainViewModel) {
    NavHost(navController = navHostController, startDestination = Screen.RocketsScreen.route) {
        composable(route = Screen.RocketsScreen.route) {
            RocketsScreen(mainViewModel)
        }
        composable(route = Screen.UpcomingLaunchesScreen.route) {
            UpcomingLaunchesScreen(mainViewModel)
        }
        composable(route = Screen.PastLaunchesScreen.route) {
            PastLaunchesScreen(mainViewModel)
        }
        composable(route = Screen.CompanyInfoScreen.route) {
            CompanyInfoScreen(mainViewModel)
        }
    }
}