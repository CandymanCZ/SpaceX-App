package com.kotlin.spacexapp

import retrofit2.Call
import retrofit2.http.GET

interface GetPastLaunchesService {
    @GET("/v5/launches/past")
    fun fetchPastLaunches(): Call<List<PastLaunch>>
}