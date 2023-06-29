package com.kotlin.spacexapp.mainfunctions

import android.content.Context
import android.widget.Toast
import com.kotlin.spacexapp.PastLaunch
import com.kotlin.spacexapp.Rocket

class FilterPastLaunches {
    operator fun invoke(
        from: String,
        to: String,
        rocketName: String,
        successfulOption: String,
        pastLaunchesList: List<PastLaunch>,
        rocketList: List<Rocket>
    ) : MutableList<PastLaunch> {
        var selectedFilterRocketId: String = ""
        val filtered: MutableList<PastLaunch> = pastLaunchesList.toMutableList()

        // First get rocket ID from it's name
        for (rocket in rocketList) {
            if (rocket.name == rocketName) {
                // println("Rocket id =  " + rocket.id)
                selectedFilterRocketId = rocket.id!!
                break
            }
        }

        for (pastLaunch: PastLaunch in pastLaunchesList) {

            // Filter by years
            val date = UnixSecondsToDate()(pastLaunch.dateUnix!!)
            if (date.year < from.toInt() || date.year > to.toInt()) {
                filtered.remove(pastLaunch)
            }

            // Filter by rocket
            if (rocketName != "Any") {
                if (pastLaunch.rocket != selectedFilterRocketId) {
                    filtered.remove(pastLaunch)
                }
            }

            // Filter by success
            if (successfulOption != "Any" && pastLaunch.success != null) {
                if (successfulOption == "Successful") {
                    if (!pastLaunch.success!!) {
                        filtered.remove(pastLaunch)
                    }
                } else {
                    if (pastLaunch.success!!) {
                        filtered.remove(pastLaunch)
                    }
                }
            }
        }
        return filtered
    }
}