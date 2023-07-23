package com.kotlin.spacexapp.mainfunctions

import android.content.Context
import com.kotlin.spacexapp.repositories.MainRepository
import com.kotlin.spacexapp.viewmodels.MainViewModel
import com.kotlin.spacexapp.api.RetrofitClientInstance
import com.kotlin.spacexapp.Rocket
import com.squareup.moshi.Types
import javax.inject.Inject

class RetrieveAndParseJson @Inject constructor(
    private val mainRepository: MainRepository
    ) {
    operator fun invoke(context: Context) : List<Rocket>? {
        val string: String = mainRepository.retrieveJson(context)
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