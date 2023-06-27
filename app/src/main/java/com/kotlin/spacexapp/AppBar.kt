package com.kotlin.spacexapp

import android.content.Context
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun AppBar(
    onNavigationIconClick: () -> Unit,
    navHostController: NavHostController,
    mainViewModel: MainViewModel,
    context: Context
) {
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val title = mutableStateOf("")
    val currentRoute = navBackStackEntry?.destination?.route

    when(currentRoute) {
        Screen.RocketsScreen.route -> title.value = "Rockets"
        Screen.UpcomingLaunchesScreen.route -> title.value = "Upcoming launches"
        Screen.PastLaunchesScreen.route -> title.value = "Past launches"
        Screen.CompanyInfoScreen.route -> title.value = "Company info"
    }

    TopAppBar(
        title = {
            Text(text = title.value)
        },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        navigationIcon = {
            IconButton(onClick = onNavigationIconClick) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Open menu"
                )
            }
        },
        actions = {
            IconButton(onClick = {
                when (currentRoute) {
                    Screen.RocketsScreen.route -> mainViewModel.fetchRockets(context)
                    Screen.UpcomingLaunchesScreen.route -> mainViewModel.fetchUpcomingLaunches(context)
                    Screen.PastLaunchesScreen.route -> mainViewModel.fetchPastLaunches(context)
                    Screen.CompanyInfoScreen.route -> mainViewModel.fetchCompanyInfo(context)
                }
            }) {
                Icon(imageVector = Icons.Default.Refresh, contentDescription = "Refresh")
            }
            if (currentRoute == Screen.PastLaunchesScreen.route) {
                IconButton(onClick = {
                    mainViewModel.openFilterDialog.value = true
                }) {
                    Icon(imageVector = Icons.Default.FilterList, contentDescription = "Refresh")
                }
            }

        }
    )
}