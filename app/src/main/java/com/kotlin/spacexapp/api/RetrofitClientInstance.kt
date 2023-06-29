package com.kotlin.spacexapp.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitClientInstance {
    companion object {
        private var retrofit: Retrofit? = null
        val moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

        fun getInstance(): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl("https://api.spacexdata.com/") // Space-X API base URL
                    .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
                    .build()
            }
            return retrofit!!
        }
    }
}