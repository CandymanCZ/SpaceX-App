package com.kotlin.spacexapp.mainfunctions

class CheckFilterYears {
    operator fun invoke(from: String, to: String): Boolean {
        return from.toInt() <= to.toInt()
    }
}