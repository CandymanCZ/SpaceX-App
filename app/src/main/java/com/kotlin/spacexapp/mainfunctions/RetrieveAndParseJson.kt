package com.kotlin.spacexapp.mainfunctions

import android.content.Context
import com.kotlin.spacexapp.repositories.MainRepository
import com.kotlin.spacexapp.viewmodels.MainViewModel
import com.kotlin.spacexapp.api.RetrofitClientInstance
import com.kotlin.spacexapp.Rocket
import com.squareup.moshi.Types

class RetrieveAndParseJson(mainRepository: MainRepository) {
    operator fun invoke(context: Context) : List<Rocket>? {
        val string: String = MainViewModel.mMainRepository.retrieveJson(context)
        if (string != "empty") {
            return RetrofitClientInstance.moshi
                .adapter<List<Rocket>>(
                    Types.newParameterizedType(
                        List::class.java,
                        Rocket::class.java
                    )
                )
                .fromJson(string)!!
        }
        return null
    }
}