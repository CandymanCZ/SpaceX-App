package com.kotlin.spacexapp.ui.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.platform.LocalContext
import com.kotlin.spacexapp.MainViewModel
import com.kotlin.spacexapp.RocketCard

@Composable
fun RocketsScreen(mainViewModel: MainViewModel) {
    val context = LocalContext.current
    mainViewModel.startScreenProcedure(context)
    Column(
        modifier = Modifier.fillMaxSize().background(LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyColumn {
            items(mainViewModel.rocketList.value) {rocket ->
                RocketCard(rocket, mainViewModel)
            }
        }
    }
}