package com.kotlin.spacexapp

import retrofit2.Call
import retrofit2.http.GET

interface GetRocketsService {
    @GET("/v4/rockets")
    fun fetchRockets(): Call<List<Rocket>>

}