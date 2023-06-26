package com.kotlin.spacexapp

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun UpcomingLaunchesScreen(mainViewModel: MainViewModel) {
    val context = LocalContext.current
    mainViewModel.fetchUpcomingLaunches(context)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn {
            items(mainViewModel.upcomingLaunchesList.value) {upcomingLaunch ->
                UpcomingLaunchCard(upcomingLaunch, mainViewModel)
            }
        }
    }
}