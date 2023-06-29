package com.kotlin.spacexapp.ui.app.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kotlin.spacexapp.viewmodels.MainViewModel
import com.kotlin.spacexapp.PastLaunch


@Composable
fun PastLaunchCard(pastLaunch: PastLaunch, mainViewModel: MainViewModel) {
    val context = LocalContext.current
    Card(
        elevation = 4.dp,
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable { mainViewModel.openPastLaunchDetailScreen(pastLaunch, context) },
        backgroundColor = Color.White,
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .width(100.dp)
                    .height(100.dp)
            ) {
                var patchURL: String = "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/No_image_available.svg/300px-No_image_available.svg.png"
                if (pastLaunch.links?.patch?.small != null) {
                    patchURL = pastLaunch.links.patch.small
                }
                AsyncImage(
                    modifier = Modifier
                        .padding(10.dp),
                    model = patchURL,
                    contentDescription = "Patch image",
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = pastLaunch.name!!,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    modifier = Modifier.padding(10.dp),
                    text = mainViewModel.mainFunctions.getRocketNameFromId(pastLaunch.rocket!!, mainViewModel.rocketList.value)!!
                )
            }


        }
    }
}