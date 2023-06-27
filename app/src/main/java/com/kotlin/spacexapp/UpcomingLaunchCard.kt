package com.kotlin.spacexapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

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
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            var patchURL: String = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/300px-No_image_available.svg.png"
            if (upcomingLaunch.links?.patch?.small != null) {
                patchURL = upcomingLaunch.links.patch.small
            }
            AsyncImage(
                modifier = Modifier
                    .padding(10.dp),
                model = patchURL,
                contentDescription = "Rocket image",
            )
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                modifier = Modifier.padding(10.dp),
                text = upcomingLaunch.name!!,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}