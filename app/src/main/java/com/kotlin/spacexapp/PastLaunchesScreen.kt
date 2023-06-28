package com.kotlin.spacexapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.kotlin.spacexapp.ui.theme.LightGrey

@Composable
fun PastLaunchesScreen(mainViewModel: MainViewModel) {
    val context = LocalContext.current
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