package com.kotlin.spacexapp.api

import com.kotlin.spacexapp.UpcomingLaunch
import retrofit2.Call
import retrofit2.http.GET

interface GetUpcomingLaunchesService {
    @GET("/v5/launches/upcoming")
    fun fetchUpcomingLaunches(): Call<List<UpcomingLaunch>>
}