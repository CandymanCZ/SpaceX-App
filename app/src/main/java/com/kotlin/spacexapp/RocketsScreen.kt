package com.kotlin.spacexapp

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
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RocketsScreen(mainViewModel: MainViewModel) {
    val context = LocalContext.current
    mainViewModel.startScreenProcedure(context)
    mainViewModel.fetchRockets(context = context)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn {
            items(mainViewModel.rocketList.value) {rocket ->
                RocketCard(rocket, mainViewModel)
            }
        }
    }
}