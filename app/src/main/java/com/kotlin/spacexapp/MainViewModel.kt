package com.kotlin.spacexapp

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.kotlin.spacexapp.RetrofitClientInstance.Companion.moshi
import com.squareup.moshi.Types
import java.sql.Timestamp
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


class MainViewModel: ViewModel() {
    companion object {
        val mMainRepository: MainRepository = MainRepository()
    }
    val rocketList = mutableStateOf(listOf<Rocket>())
    val companyInfo = mutableStateOf(GetCompanyInfoResponse())
    val upcomingLaunchesList = mutableStateOf(listOf<UpcomingLaunch>())
    val pastLaunchesList = mutableStateOf(listOf<PastLaunch>())
    val pastLaunchesFilteredList = mutableStateOf(listOf<PastLaunch>())
    val openFilterDialog = mutableStateOf(false)
    val selectedTextFrom = mutableStateOf("2006")
    val selectedTextTo = mutableStateOf("2006")
    val pastLaunchesFilterEnabled = mutableStateOf(false)
    val selectedFilterRocketName = mutableStateOf("Any")
    var selectedFilterRocketId: String = ""
    val selectedFlightSuccessOption = mutableStateOf("Any")
    val isProgressBarEnabled = mutableStateOf(false)


    fun fetchRockets(context: Context) {
        isProgressBarEnabled.value = true
        mMainRepository.getRocketsRemote(object : MainRepository.IGetRocketsResponse {
            override fun onResponse(getRocketsResponse: List<Rocket>?) {
                println("Success!!!!!!!!!!!!!!!")
                rocketList.value = getRocketsResponse!!
                saveJson(context)
                isProgressBarEnabled.value = false
            }

            override fun onFailure(t: Throwable) {
                Toast.makeText(context, "Connection error", Toast.LENGTH_SHORT).show()
                println(t.localizedMessage)
                isProgressBarEnabled.value = false
            }
        })
    }


    fun fetchCompanyInfo(context: Context) {
        isProgressBarEnabled.value = true
        mMainRepository.getCompanyInfoRemote(object : MainRepository.IGetCompanyInfoResponse {
            override fun onResponse(getCompanyInfoResponse: GetCompanyInfoResponse?) {
                println("Success!!!!!!!!!!!!!!!")
                companyInfo.value = getCompanyInfoResponse!!
                isProgressBarEnabled.value = false
            }

            override fun onFailure(t: Throwable) {
                Toast.makeText(context, "Connection error", Toast.LENGTH_SHORT).show()
                println(t.localizedMessage)
                isProgressBarEnabled.value = false
            }
        })
    }


    fun fetchUpcomingLaunches(context: Context) {
        isProgressBarEnabled.value = true
        mMainRepository.getUpcomingLaunchesRemote(object : MainRepository.IGetUpcomingLaunchesResponse {
            override fun onResponse(getUpcomingLaunchesResponse: List<UpcomingLaunch>?) {
                println("Success!!!!!!!!!!!!!!!")
                upcomingLaunchesList.value = getUpcomingLaunchesResponse!!
                isProgressBarEnabled.value = false
            }

            override fun onFailure(t: Throwable) {
                Toast.makeText(context, "Connection error", Toast.LENGTH_SHORT).show()
                println(t.localizedMessage)
                isProgressBarEnabled.value = false
            }
        })
    }


    fun fetchPastLaunches(context: Context) {
        isProgressBarEnabled.value = true
        mMainRepository.getPastLaunchesRemote(object : MainRepository.IGetPastLaunchesResponse {
            override fun onResponse(getPastLaunchesResponse: List<PastLaunch>?) {
                println("Success!!!!!!!!!!!!!!!")
                pastLaunchesList.value = getPastLaunchesResponse!!
                isProgressBarEnabled.value = false

            }

            override fun onFailure(t: Throwable) {
                Toast.makeText(context, "Connection error", Toast.LENGTH_SHORT).show()
                println(t.localizedMessage)
                isProgressBarEnabled.value = false
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


    fun openPastLaunchDetailScreen(pastLaunch: PastLaunch, context: Context) {
        val intent = Intent(context, PastLaunchDetailActivity::class.java)
        intent.putExtra("launch", pastLaunch)
        context.startActivity(intent)
    }


    fun checkFilterYears(from: String, to: String) : Boolean {
        return from.toInt() <= to.toInt()
    }


    fun getRocketNameFromId(rocketId: String?) : String? {
        for (rocket: Rocket in rocketList.value) {
            if (rocketId == rocket.id) {
                return  rocket.name
            }
        }
        return null
    }


    fun applyPastLaunchesFilter(
        context: Context,
        from: String,
        to: String,
        rocketName: String,
        successfulOption: String
    ) {
        // First get rocket ID from it's name
        for (rocket in rocketList.value) {
            if (rocket.name == rocketName) {
                selectedFilterRocketId = rocket.id!!
            }
        }
        val filtered: MutableList<PastLaunch> = pastLaunchesList.value.toMutableList()
        for (pastLaunch: PastLaunch in pastLaunchesList.value) {
            val timeStamp = Timestamp(pastLaunch.dateUnix!! * 1000)
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")
            val date = LocalDate.parse(timeStamp.toString(), formatter)

            // Filter by years
            if (date.year < from.toInt() || date.year > to.toInt()) {
                filtered.remove(pastLaunch)
            }

            // Filter by rocket
            if (rocketName != "Any") {
                if (pastLaunch.rocket != selectedFilterRocketId) {
                    filtered.remove(pastLaunch)
                }
            }

            // Filter by success
            if (successfulOption != "Any" && pastLaunch.success != null) {
                if (successfulOption == "Successful") {
                    if (!pastLaunch.success!!) {
                        filtered.remove(pastLaunch)
                    }
                } else {
                    if (pastLaunch.success!!) {
                        filtered.remove(pastLaunch)
                    }
                }
            }
        }

        if (filtered.isEmpty()) {
            Toast.makeText(context, "No results for your filter", Toast.LENGTH_SHORT).show()
            return
        }
        pastLaunchesFilteredList.value = filtered
        pastLaunchesFilterEnabled.value = true
    }
}