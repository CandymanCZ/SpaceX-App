package com.kotlin.spacexapp.ui.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kotlin.spacexapp.viewmodels.MainViewModel
import com.kotlin.spacexapp.UpcomingLaunchCard
import com.kotlin.spacexapp.ui.theme.LightGrey

@Composable
fun UpcomingLaunchesScreen(mainViewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightGrey),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn {
            items(mainViewModel.upcomingLaunchesList.value) {upcomingLaunch ->
                UpcomingLaunchCard(upcomingLaunch, mainViewModel)
            }
        }
    }
}