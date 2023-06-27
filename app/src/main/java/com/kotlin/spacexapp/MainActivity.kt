package com.kotlin.spacexapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Rocket
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.kotlin.spacexapp.ui.theme.SpaceXAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel.startScreenProcedure(this)
        mainViewModel.fetchRockets(this)
        mainViewModel.fetchPastLaunches(this)
        mainViewModel.fetchUpcomingLaunches(this)
        mainViewModel.fetchCompanyInfo(this)
        setContent {
            SpaceXAppTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                val openFilterDialog =  remember {mainViewModel.openFilterDialog}
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                             AppBar(
                                 context = this,
                                 mainViewModel = mainViewModel,
                                 navHostController = navController,
                                 onNavigationIconClick = {
                                     scope.launch {
                                         scaffoldState.drawerState.open()
                                     }
                                 }
                             )
                    },
                    drawerShape = RoundedCornerShape(20.dp),
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

                            // Filter dialog
                            if (openFilterDialog.value) {
                                androidx.compose.material3.AlertDialog(
                                    onDismissRequest = {
                                        openFilterDialog.value = false
                                    },
                                ) {
                                    Surface(
                                        modifier = Modifier
                                            .wrapContentWidth()
                                            .wrapContentHeight(),
                                        shape = RoundedCornerShape(15.dp)
                                    ) {
                                        Column(modifier = Modifier
                                            .padding(16.dp)
                                            .wrapContentHeight()
                                        ) {
                                            Column(
                                                modifier = Modifier
                                                    .wrapContentHeight()
                                                    .fillMaxWidth(),
                                                horizontalAlignment = Alignment.CenterHorizontally
                                            ) {
                                                Text(
                                                    modifier = Modifier.padding(bottom = 20.dp),
                                                    text = "Filters",
                                                    fontSize = 20.sp,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }
                                            Row(modifier = Modifier
                                                .fillMaxWidth()
                                                .wrapContentHeight(),
                                                horizontalArrangement = Arrangement.End,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Text(modifier = Modifier.padding(
                                                    start = 10.dp,
                                                    end = 26.dp),
                                                    text = "From"
                                                )
                                                FromYearDropdownMenuBox(mainViewModel)
                                            }
                                            Row(modifier = Modifier
                                                .fillMaxWidth()
                                                .wrapContentHeight(),
                                                horizontalArrangement = Arrangement.End,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Text(modifier = Modifier.padding(
                                                    start = 10.dp,
                                                    end = 44.dp),
                                                    text = "To"
                                                )
                                                ToYearDropdownMenuBox(mainViewModel)
                                            }
                                            Row(modifier = Modifier
                                                .fillMaxWidth()
                                                .wrapContentHeight(),
                                                horizontalArrangement = Arrangement.End,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Text(modifier = Modifier.padding(start = 10.dp, end = 12.dp), text = "Rocket")
                                                RocketDropdownMenuBox(mainViewModel)
                                            }
                                            Row(modifier = Modifier
                                                .fillMaxWidth()
                                                .wrapContentHeight(),
                                                horizontalArrangement = Arrangement.End,
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Text(modifier = Modifier.padding(start = 10.dp), text = "Success")
                                                SuccessDropdownMenuBox(mainViewModel)
                                            }
                                            Row(
                                                modifier = Modifier
                                                    .wrapContentHeight()
                                                    .wrapContentHeight()
                                            ) {
                                                Button(onClick = {
                                                    mainViewModel.pastLaunchesFilterEnabled.value = false
                                                    openFilterDialog.value = false
                                                }) {
                                                    Text(text = "Remove filter")
                                                }
                                                Spacer(modifier = Modifier.width(50.dp))
                                                Button(onClick = {
                                                    if (mainViewModel.checkFilterYears(
                                                            mainViewModel.selectedTextFrom.value,
                                                            mainViewModel.selectedTextTo.value
                                                        )) {
                                                        mainViewModel.applyPastLaunchesFilter(
                                                            context = this@MainActivity,
                                                            from = mainViewModel.selectedTextFrom.value,
                                                            to = mainViewModel.selectedTextTo.value,
                                                            rocketName = mainViewModel.selectedFilterRocketName.value,
                                                            successfulOption = mainViewModel.selectedFlightSuccessOption.value
                                                        )
                                                        openFilterDialog.value = false
                                                    } else {
                                                        Toast.makeText(this@MainActivity, "Please input valid year range", Toast.LENGTH_SHORT).show()
                                                    }

                                                }) {
                                                    Text(text = "Set filter")
                                                }
                                            }
                                        }
                                    }
                                }
                            }
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