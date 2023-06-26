package com.kotlin.spacexapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UpcomingLaunchCard(upcomingLaunch: UpcomingLaunch, mainViewModel: MainViewModel) {
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { /*TODO*/ },
        backgroundColor = Color.White,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row() {
            Text(
                modifier = Modifier.padding(10.dp),
                text = upcomingLaunch.name!!,
                fontSize = 15.sp
            )
        }
    }
}