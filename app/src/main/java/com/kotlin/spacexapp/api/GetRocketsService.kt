package com.kotlin.spacexapp.api

import com.kotlin.spacexapp.Rocket
import retrofit2.Call
import retrofit2.http.GET

interface GetRocketsService {
    @GET("/v4/rockets")
    fun fetchRockets(): Call<List<Rocket>>

}