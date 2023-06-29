package com.kotlin.spacexapp.activities

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.kotlin.spacexapp.PastLaunch
import com.kotlin.spacexapp.ui.theme.SpaceXAppTheme
import java.sql.Timestamp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class PastLaunchDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var pastLaunch: PastLaunch = PastLaunch()

        // getSerializableExtra was deprecated so a version check has to be done
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            pastLaunch = intent.getSerializableExtra("launch", PastLaunch::class.java)!!
        } else {
            pastLaunch = intent.getSerializableExtra("launch") as PastLaunch
        }

        val timeStamp = Timestamp(pastLaunch.dateUnix!! * 1000)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")
        val date = LocalDate.parse(timeStamp.toString(), formatter)

        val successString: String = when(pastLaunch.success) {
            true -> "Successful"
            false -> "Failed"
            null -> "Unknown"
        }

        setContent {
            SpaceXAppTheme {
                Column(
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Column(
                        modifier = Modifier
                            .wrapContentHeight()
                            .fillMaxWidth()
                            .padding(15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(top = 10.dp, bottom = 10.dp),
                            text = pastLaunch.name!!,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            textDecoration = TextDecoration.Underline,
                            fontSize = 35.sp
                        )
                    }
                    Column(modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth()
                        .wrapContentHeight()
                    ) {
                        if (pastLaunch.details != null) {
                            Text(text = pastLaunch.details!!)
                        }
                        Spacer(modifier = Modifier.height(20.dp))

                        Text(text = "Date of launch: " + date.dayOfMonth + "." + date.monthValue + "." + date.year)
                        Text(text = "Success: $successString")
                        Spacer(modifier = Modifier.height(20.dp))

                        AsyncImage(
                            modifier = Modifier
                                .padding(10.dp),
                            model = pastLaunch.links!!.patch!!.large,
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
fun DefaultPreview3() {
    SpaceXAppTheme {

    }
}