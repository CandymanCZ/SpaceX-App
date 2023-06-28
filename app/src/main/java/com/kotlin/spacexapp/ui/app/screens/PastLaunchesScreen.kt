package com.kotlin.spacexapp.ui.app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.kotlin.spacexapp.MainViewModel
import com.kotlin.spacexapp.PastLaunch
import com.kotlin.spacexapp.PastLaunchCard
import com.kotlin.spacexapp.ui.theme.LightGrey

@Composable
fun PastLaunchesScreen(mainViewModel: MainViewModel) {
    var itemList: List<PastLaunch>
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = LightGrey),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn {
            if (mainViewModel.pastLaunchesFilterEnabled.value) {
                itemList = mainViewModel.pastLaunchesFilteredList.value
            } else {
                itemList = mainViewModel.pastLaunchesList.value
            }
            items(itemList) {pastLaunch ->
                PastLaunchCard(pastLaunch, mainViewModel)
            }
        }
    }
}