package com.kotlin.spacexapp

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.kotlin.spacexapp.RetrofitClientInstance.Companion.moshi
import com.kotlin.spacexapp.api.GetCompanyInfoResponse
import com.kotlin.spacexapp.mainfunctions.MainFunctions
import com.squareup.moshi.Types
import java.sql.Timestamp
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class MainViewModel: ViewModel() {
    companion object {
        val mMainRepository: MainRepository = MainRepository()
    }
    private val mainFunctions: MainFunctions = MainFunctions(mMainRepository)

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
    private var selectedFilterRocketId: String = ""
    val selectedFlightSuccessOption = mutableStateOf("Any")
    val isProgressBarEnabled = mutableStateOf(false)


    fun fetchRockets(context: Context) {
        isProgressBarEnabled.value = true
        mMainRepository.getRocketsRemote(object : MainRepository.IGetRocketsResponse {
            override fun onResponse(getRocketsResponse: List<Rocket>?) {
                rocketList.value = getRocketsResponse!!
                saveJson(context, rocketList.value)
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
    fun saveJson(context: Context, rocketList: List<Rocket>) {
        mainFunctions.convertToJsonAndSave(context, rocketList)
    }


    // Recovers json and parses it into Rocket objects if found
    fun startScreenProcedure(context: Context) {
        val retrievedRocketList: List<Rocket>? = mainFunctions.retrieveAndParseJson(context)
        if (retrievedRocketList != null) {
            rocketList.value = retrievedRocketList
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
        return mainFunctions.checkFilterYears(from, to)
    }


    fun getRocketNameFromId(rocketId: String?) : String? {
        return mainFunctions.getRocketNameFromId(rocketId, rocketList.value)
    }


    fun applyPastLaunchesFilter(
        context: Context,
        from: String,
        to: String,
        rocketName: String,
        successfulOption: String
    ) {
        val filtered: MutableList<PastLaunch> = pastLaunchesList.value.toMutableList()

        // First get rocket ID from it's name
        for (rocket in rocketList.value) {
            if (rocket.name == rocketName) {
                selectedFilterRocketId = rocket.id!!
            }
        }

        for (pastLaunch: PastLaunch in pastLaunchesList.value) {

            // Filter by years
            val date = mainFunctions.unixSecondsToDate(pastLaunch.dateUnix!!)
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