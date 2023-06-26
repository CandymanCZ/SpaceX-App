package com.kotlin.spacexapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kotlin.spacexapp.RetrofitClientInstance.Companion.moshi
import com.kotlin.spacexapp.ui.theme.SpaceXAppTheme
import com.squareup.moshi.JsonAdapter

class RocketDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val jsonString: String? = intent.getStringExtra("json")
        val jsonAdapter: JsonAdapter<Rocket> = moshi.adapter(Rocket::class.java)
        val rocket: Rocket = jsonAdapter.fromJson(jsonString)!!
        setContent {
            SpaceXAppTheme {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(10.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(top = 10.dp),
                            text = rocket.name,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.Underline,
                            fontSize = 20.sp
                        )
                    }
                    Text(text = rocket.description!!)
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "Type: " + rocket.type)
                    Text(text = "Stages: " + rocket.stages)
                    Text(text = "Boosters: " + rocket.boosters)
                    Text(text = "Height: " + rocket.height.meters + " meters")
                    Text(text = "Diameter: " + rocket.diameter.meters + " meters")
                    Text(text = "Mass: " + rocket.mass.kg + " kilograms")
                    Text(text = "Engines: " + rocket.engines.number.toString())
                    Text(text = "Landing legs: " + rocket.landingLegs.number.toString())
                    Text(text = "Cost per launch: " + rocket.costPerLaunch + " USD")

                    Spacer(modifier = Modifier.height(20.dp))
                    for (imageURL: String? in rocket.flickrImages) {
                        AsyncImage(
                            modifier = Modifier
                                .padding(10.dp),
                            model = imageURL,
                            contentDescription = "Rocket image",
                        )
                    }

                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    SpaceXAppTheme {
        
    }
}