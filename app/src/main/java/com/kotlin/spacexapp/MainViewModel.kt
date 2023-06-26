package com.kotlin.spacexapp

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.kotlin.spacexapp.RetrofitClientInstance.Companion.moshi
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.adapter


class MainViewModel: ViewModel() {
    companion object {
        val mMainRepository: MainRepository = MainRepository()
    }
    val rocketList = mutableStateOf(listOf<Rocket>())

    fun fetchRockets() {
        mMainRepository.getRocketsRemote(object : MainRepository.IGetRocketsResponse {
            override fun onResponse(getRocketsResponse: List<Rocket>?) {
                println("Success!!!!!!!!!!!!!!!")
                rocketList.value = getRocketsResponse!!
            }

            override fun onFailure(t: Throwable) {
                println("Failure :(((((((((((((")
                println(t.localizedMessage)
            }
        })
    }


    fun openRocketDetailScreen(rocket: Rocket, context: Context) {
        // Convert the rocket object to raw json before passing it as an intent extra
        val jsonAdapter: JsonAdapter<Rocket> = moshi.adapter(Rocket::class.java)
        val jsonString: String = jsonAdapter.toJson(rocket)

        val intent = Intent(context, RocketDetailActivity::class.java)
        intent.putExtra("json", jsonString)
        context.startActivity(intent)
    }
}