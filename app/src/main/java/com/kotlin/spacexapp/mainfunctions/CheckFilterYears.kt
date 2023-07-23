package com.kotlin.spacexapp.mainfunctions

import javax.inject.Inject

class CheckFilterYears @Inject constructor() {
    operator fun invoke(from: String, to: String): Boolean {
        return from.toInt() <= to.toInt()
    }
}