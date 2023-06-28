package com.kotlin.spacexapp

import android.content.Context
import androidx.compose.foundation.background
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
import com.kotlin.spacexapp.ui.theme.LightGrey

@Composable
fun UpcomingLaunchesScreen(mainViewModel: MainViewModel) {
    val context = LocalContext.current
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