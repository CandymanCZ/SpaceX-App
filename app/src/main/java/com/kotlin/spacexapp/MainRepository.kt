package com.kotlin.spacexapp

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository {

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


    interface IGetRocketsResponse {
        fun onResponse(getRocketsResponse: List<Rocket>?)
        fun onFailure(t: Throwable)
    }
}