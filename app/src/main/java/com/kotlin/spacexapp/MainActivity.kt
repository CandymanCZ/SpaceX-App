package com.kotlin.spacexapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import com.kotlin.spacexapp.ui.theme.SpaceXAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpaceXAppTheme {
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                             AppBar(
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
                                    id = "Rockets",
                                    title = "Rocket Info",
                                    icon = Icons.Default.Rocket,
                                    contentDescription = " Go to rocket screen"
                                ),
                                MenuItem(
                                    id = "Past launches",
                                    title = "Past launches",
                                    icon = Icons.Default.RocketLaunch,
                                    contentDescription = "Go to past launches"
                                ),
                                MenuItem(
                                    id = "Upcoming launches",
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
                                println("Clicked on ${it.title}")
                            }
                        )
                    },
                    content = {padding ->
                        Surface(
                            modifier = Modifier
                                .padding(1.dp)
                                .fillMaxSize()
                        ) {
                            Navigation()
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