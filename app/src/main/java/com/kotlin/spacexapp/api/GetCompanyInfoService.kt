package com.kotlin.spacexapp.api

import com.kotlin.spacexapp.api.GetCompanyInfoResponse
import retrofit2.Call
import retrofit2.http.GET

interface GetCompanyInfoService {
    @GET("/v4/company")
    fun fetchCompanyInfo(): Call<GetCompanyInfoResponse>
}