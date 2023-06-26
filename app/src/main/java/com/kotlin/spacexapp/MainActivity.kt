package com.kotlin.spacexapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Rocket
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.kotlin.spacexapp.ui.theme.SpaceXAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.startScreenProcedure(this)
        setContent {
            SpaceXAppTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                             AppBar(
                                 navHostController = navController,
                                 onNavigationIconClick = {
                                     scope.launch {
                                         scaffoldState.drawerState.open()
                                     }
                                 }
                             )
                    },
                    drawerContent = {
                        DrawerHeader()
                        DrawerBody(
                            items = listOf(
                                MenuItem(
                                    id = "rockets",
                                    title = "Rocket Info",
                                    icon = Icons.Default.Rocket,
                                    contentDescription = " Go to rocket screen"
                                ),
                                MenuItem(
                                    id = "past_launches",
                                    title = "Past launches",
                                    icon = Icons.Default.RocketLaunch,
                                    contentDescription = "Go to past launches"
                                ),
                                MenuItem(
                                    id = "upcoming_launches",
                                    title = "Upcoming launches",
                                    icon = Icons.Default.RocketLaunch,
                                    contentDescription = "Go to upcoming launches"
                                ),
                                MenuItem(
                                    id = "info",
                                    title = "Company info",
                                    icon = Icons.Default.Info,
                                    contentDescription = "Go to company info"
                                ),

                            ),
                            onItemClick = {
                                when (it.id) {
                                    "rockets" -> navController.navigate(Screen.RocketsScreen.route)
                                    "upcoming_launches" -> navController.navigate(Screen.UpcomingLaunchesScreen.route)
                                    "past_launches" -> navController.navigate(Screen.PastLaunchesScreen.route)
                                    "info" -> navController.navigate(Screen.CompanyInfoScreen.route)
                                }
                                scope.launch {
                                    scaffoldState.drawerState.close()
                                }
                            }
                        )
                    },
                    content = {padding ->
                        Surface(
                            modifier = Modifier
                                .padding(1.dp)
                                .fillMaxSize()
                        ) {
                            Navigation(navController, mainViewModel)
                        }

                    }

                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SpaceXAppTheme {

    }
}