package com.kotlin.spacexapp

sealed class Screen(val route: String) {
    object RocketsScreen : Screen("rockets_screen")
    object PastLaunchesScreen : Screen("past_launches_screen")
    object UpcomingLaunchesScreen : Screen("upcoming_launches_screen")
    object CompanyInfoScreen: Screen("company_info_screen")
}
