package com.kotlin.spacexapp.mainfunctions

import android.content.Context
import com.kotlin.spacexapp.repositories.MainRepository
import com.kotlin.spacexapp.api.RetrofitClientInstance
import com.kotlin.spacexapp.Rocket
import com.squareup.moshi.Types
import javax.inject.Inject

class ConvertToJsonAndSave @Inject constructor(
    private val mainRepository: MainRepository
    ) {
    operator fun invoke(context: Context, rocketList: List<Rocket>) {

        // Convert the whole rocket list into json before saving it into shared preferences
        val jsonString: String = RetrofitClientInstance.moshi
            .adapter<List<Rocket>>(Types.newParameterizedType(List::class.java, Rocket::class.java))
            .toJson(rocketList)
        mainRepository.saveJson(jsonString, context)

    }
}