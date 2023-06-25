package com.kotlin.spacexapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    companion object {
        val mMainRepository: MainRepository = MainRepository()
    }

    fun fetchRockets() {
        mMainRepository.getRocketsRemote(object : MainRepository.IGetRocketsResponse {
            override fun onResponse(getRocketsResponse: List<Rocket>?) {
                println("Success!!!!!!!!!!!!!!!")
            }

            override fun onFailure(t: Throwable) {
                println("Failure :(((((((((((((")
                println(t.localizedMessage)
            }
        })
    }
}