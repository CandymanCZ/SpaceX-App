package com.kotlin.spacexapp.mainfunctions

import com.kotlin.spacexapp.repositories.MainRepository
import javax.inject.Inject

data class MainFunctions @Inject constructor(
    val mainRepository: MainRepository,

    val getRocketNameFromId: GetRocketNameFromId,
    val checkFilterYears: CheckFilterYears,
    val unixSecondsToDate: UnixSecondsToDate,
    val convertToJsonAndSave: ConvertToJsonAndSave,
    val retrieveAndParseJson: RetrieveAndParseJson,
    val filterPastLaunches: FilterPastLaunches
)
