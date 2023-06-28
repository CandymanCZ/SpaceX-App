package com.kotlin.spacexapp.mainfunctions

import java.sql.Timestamp
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class UnixSecondsToDate {
    operator fun invoke(unixSeconds: Long): LocalDate {
        val timeStamp = Timestamp(unixSeconds * 1000)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S")

        return LocalDate.parse(timeStamp.toString(), formatter)
    }
}