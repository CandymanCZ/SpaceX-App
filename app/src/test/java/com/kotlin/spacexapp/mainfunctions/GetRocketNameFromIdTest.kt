package com.kotlin.spacexapp.mainfunctions

import com.google.common.truth.Truth.assertThat
import com.kotlin.spacexapp.Rocket
import org.junit.Before
import org.junit.Test

class GetRocketNameFromIdTest {
    private var rocketList: MutableList<Rocket> = mutableListOf<Rocket>()
    private lateinit var getRocketNameFromId: GetRocketNameFromId

    @Before
    fun setUp() {
        for (i in 0..20) {
            rocketList.add(Rocket(id = i.toString(), name = i.toString()))
        }
        getRocketNameFromId = GetRocketNameFromId()
    }

    @Test
    fun `Get rocket name based on it's ID` () {
        for (rocket in rocketList) {
            assertThat(getRocketNameFromId(rocketId = rocket.id, rocketList) == rocket.name)
        }
    }
}