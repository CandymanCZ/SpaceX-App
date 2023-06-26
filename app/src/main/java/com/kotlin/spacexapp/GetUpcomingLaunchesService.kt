package com.kotlin.spacexapp

import retrofit2.Call
import retrofit2.http.GET

interface GetUpcomingLaunchesService {
    @GET("/v5/launches/upcoming")
    fun fetchUpcomingLaunches(): Call<List<UpcomingLaunch>>
}