package com.kotlin.spacexapp.mainfunctions

import com.kotlin.spacexapp.MainRepository

data class MainFunctions(
    val mainRepository: MainRepository,

    val getRocketNameFromId: GetRocketNameFromId = GetRocketNameFromId(),
    val checkFilterYears: CheckFilterYears = CheckFilterYears(),
    val unixSecondsToDate: UnixSecondsToDate = UnixSecondsToDate(),
    val convertToJsonAndSave: ConvertToJsonAndSave = ConvertToJsonAndSave(mainRepository),
    val retrieveAndParseJson: RetrieveAndParseJson = RetrieveAndParseJson(mainRepository)
)
