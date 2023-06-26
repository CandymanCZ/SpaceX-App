package com.kotlin.spacexapp

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.kotlin.spacexapp.RetrofitClientInstance.Companion.moshi
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Types
import java.lang.reflect.Type


class MainViewModel: ViewModel() {
    companion object {
        val mMainRepository: MainRepository = MainRepository()
    }
    val rocketList = mutableStateOf(listOf<Rocket>())
    val companyInfo = mutableStateOf(GetCompanyInfoResponse())
    val upcomingLaunchesList = mutableStateOf(listOf<UpcomingLaunch>())
    val pastLaunchesList = mutableStateOf(listOf<PastLaunch>())


    fun fetchRockets(context: Context) {
        mMainRepository.getRocketsRemote(object : MainRepository.IGetRocketsResponse {
            override fun onResponse(getRocketsResponse: List<Rocket>?) {
                println("Success!!!!!!!!!!!!!!!")
                rocketList.value = getRocketsResponse!!
                saveJson(context)
            }

            override fun onFailure(t: Throwable) {
                println("Failure :(((((((((((((")
                println(t.localizedMessage)
            }
        })
    }


    fun fetchCompanyInfo() {
        mMainRepository.getCompanyInfoRemote(object : MainRepository.IGetCompanyInfoResponse {
            override fun onResponse(getCompanyInfoResponse: GetCompanyInfoResponse?) {
                println("Success!!!!!!!!!!!!!!!")
                companyInfo.value = getCompanyInfoResponse!!
            }

            override fun onFailure(t: Throwable) {
                println("Failure :(((((((((((((")
                println(t.localizedMessage)
            }
        })
    }


    fun fetchUpcomingLaunches(context: Context) {
        mMainRepository.getUpcomingLaunchesRemote(object : MainRepository.IGetUpcomingLaunchesResponse {
            override fun onResponse(getUpcomingLaunchesResponse: List<UpcomingLaunch>?) {
                println("Success!!!!!!!!!!!!!!!")
                upcomingLaunchesList.value = getUpcomingLaunchesResponse!!
            }

            override fun onFailure(t: Throwable) {
                println("Failure :(((((((((((((")
                println(t.localizedMessage)
            }
        })
    }


    fun fetchPastLaunches(context: Context) {
        mMainRepository.getPastLaunchesRemote(object : MainRepository.IGetPastLaunchesResponse {
            override fun onResponse(getPastLaunchesResponse: List<PastLaunch>?) {
                println("Success!!!!!!!!!!!!!!!")

            }

            override fun onFailure(t: Throwable) {
                println("Failure :(((((((((((((")
                println(t.localizedMessage)
            }
        })
    }


    // Convert the whole rocket list into json before saving it into shared preferences
    fun saveJson(context: Context) {
        val jsonString: String = moshi
            .adapter<List<Rocket>>(Types.newParameterizedType(List::class.java, Rocket::class.java))
            .toJson(rocketList.value)
        mMainRepository.saveJson(jsonString, context)
    }


    // Recovers json and parses it into Rocket objects if found
    fun startScreenProcedure(context: Context) {
        val string: String = mMainRepository.retrieveJson(context)
        if (string != "empty") {
            rocketList.value = moshi
                .adapter<List<Rocket>>(Types.newParameterizedType(List::class.java, Rocket::class.java))
                .fromJson(string)!!
        }
    }


    fun openRocketDetailScreen(rocket: Rocket, context: Context) {
        val intent = Intent(context, RocketDetailActivity::class.java)
        intent.putExtra("rocket", rocket)
        context.startActivity(intent)
    }
}