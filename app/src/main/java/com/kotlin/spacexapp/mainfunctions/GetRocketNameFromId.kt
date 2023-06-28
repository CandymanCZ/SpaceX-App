package com.kotlin.spacexapp.mainfunctions

import com.kotlin.spacexapp.Rocket

class GetRocketNameFromId() {
    operator fun invoke(
        rocketId: String?,
        rocketList: List<Rocket>
    ) : String? {
        for (rocket: Rocket in rocketList) {
            if (rocketId == rocket.id) {
                return  rocket.name
            }
        }
        return null
    }
}