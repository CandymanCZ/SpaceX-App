package com.kotlin.spacexapp

import android.content.Context
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository {
    companion object {
        const val SHARED_PREFS: String = "com.kotlin.spacexapp.prefs"
    }

    fun getRocketsRemote(getRocketsResponse: IGetRocketsResponse) {
        val getRocketsService: GetRocketsService = RetrofitClientInstance.getInstance().create(GetRocketsService::class.java)
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
        val getCompanyInfoService: GetCompanyInfoService = RetrofitClientInstance.getInstance().create(GetCompanyInfoService::class.java)
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
}