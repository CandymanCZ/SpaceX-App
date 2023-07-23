package com.kotlin.spacexapp.repositories

import android.content.Context
import com.kotlin.spacexapp.PastLaunch
import com.kotlin.spacexapp.api.RetrofitClientInstance
import com.kotlin.spacexapp.Rocket
import com.kotlin.spacexapp.UpcomingLaunch
import com.kotlin.spacexapp.api.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainRepository @Inject constructor(){
    companion object {
        const val SHARED_PREFS: String = "com.kotlin.spacexapp.prefs"
    }

    fun getRocketsRemote(getRocketsResponse: IGetRocketsResponse) {
        val getRocketsService: GetRocketsService = RetrofitClientInstance.getInstance().create(
            GetRocketsService::class.java)
        val initiateGetRockets: Call<List<Rocket>> = getRocketsService.fetchRockets()

        initiateGetRockets.enqueue(object : Callback<List<Rocket>?> {
            override fun onResponse(
                call: Call<List<Rocket>?>,
                response: Response<List<Rocket>?>
            ) {
                if (response.isSuccessful) {
                    getRocketsResponse.onResponse(response.body())
                } else {
                    getRocketsResponse.onFailure(Throwable(response.message()))
                }
            }

            override fun onFailure(call: Call<List<Rocket>?>, t: Throwable) {
                getRocketsResponse.onFailure(t)
            }
        })
    }


    fun getCompanyInfoRemote(getCompanyInfoResponse: IGetCompanyInfoResponse) {
        val getCompanyInfoService: GetCompanyInfoService = RetrofitClientInstance.getInstance().create(
            GetCompanyInfoService::class.java)
        val initiateGetCompanyInfo: Call<GetCompanyInfoResponse> = getCompanyInfoService.fetchCompanyInfo()

        initiateGetCompanyInfo.enqueue(object : Callback<GetCompanyInfoResponse?> {
            override fun onResponse(
                call: Call<GetCompanyInfoResponse?>,
                response: Response<GetCompanyInfoResponse?>
            ) {
                if (response.isSuccessful) {
                    getCompanyInfoResponse.onResponse(response.body())
                } else {
                    getCompanyInfoResponse.onFailure(Throwable(response.message()))
                }
            }

            override fun onFailure(call: Call<GetCompanyInfoResponse?>, t: Throwable) {
                getCompanyInfoResponse.onFailure(t)
            }
        })
    }


    fun getUpcomingLaunchesRemote(getUpcomingLaunchesResponse: IGetUpcomingLaunchesResponse) {
        val getUpcomingLaunchesService: GetUpcomingLaunchesService = RetrofitClientInstance.getInstance()
            .create(
            GetUpcomingLaunchesService::class.java)
        val initiateGetUpcomingLaunches: Call<List<UpcomingLaunch>> = getUpcomingLaunchesService.fetchUpcomingLaunches()

        initiateGetUpcomingLaunches.enqueue(object : Callback<List<UpcomingLaunch>?> {
            override fun onResponse(
                call: Call<List<UpcomingLaunch>?>,
                response: Response<List<UpcomingLaunch>?>
            ) {
                if (response.isSuccessful) {
                    getUpcomingLaunchesResponse.onResponse(response.body())
                } else {
                    getUpcomingLaunchesResponse.onFailure(Throwable(response.message()))
                }
            }

            override fun onFailure(call: Call<List<UpcomingLaunch>?>, t: Throwable) {
                getUpcomingLaunchesResponse.onFailure(t)
            }
        })
    }


    fun getPastLaunchesRemote(getPastLaunchesResponse: IGetPastLaunchesResponse) {
        val getPastLaunchesService: GetPastLaunchesService = RetrofitClientInstance.getInstance().create(
            GetPastLaunchesService::class.java)
        val initiateGetPastLaunches: Call<List<PastLaunch>> = getPastLaunchesService.fetchPastLaunches()

        initiateGetPastLaunches.enqueue(object : Callback<List<PastLaunch>?> {
            override fun onResponse(
                call: Call<List<PastLaunch>?>,
                response: Response<List<PastLaunch>?>
            ) {
                if (response.isSuccessful) {
                    getPastLaunchesResponse.onResponse(response.body())
                } else {
                    getPastLaunchesResponse.onFailure(Throwable(response.message()))
                }
            }

            override fun onFailure(call: Call<List<PastLaunch>?>, t: Throwable) {
                getPastLaunchesResponse.onFailure(t)
            }
        })
    }


    fun saveJson(jsonString: String, context: Context) {
        val prefs = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString("json", jsonString)
        editor.apply()
    }


    fun retrieveJson(context: Context) : String {
        val prefs = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        return prefs.getString("json", "empty")!!
    }


    interface IGetRocketsResponse {
        fun onResponse(getRocketsResponse: List<Rocket>?)
        fun onFailure(t: Throwable)
    }


    interface IGetCompanyInfoResponse {
        fun onResponse(getCompanyInfoResponse: GetCompanyInfoResponse?)
        fun onFailure(t: Throwable)
    }


    interface IGetUpcomingLaunchesResponse {
        fun onResponse(getUpcomingLaunchesResponse: List<UpcomingLaunch>?)
        fun onFailure(t: Throwable)
    }


    interface IGetPastLaunchesResponse {
        fun onResponse(getPastLaunchesResponse: List<PastLaunch>?)
        fun onFailure(t: Throwable)
    }
}