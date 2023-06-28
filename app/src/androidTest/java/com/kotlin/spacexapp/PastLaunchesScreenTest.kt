package com.kotlin.spacexapp

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.kotlin.spacexapp.ui.app.screens.PastLaunchesScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PastLaunchesScreenTest {
    lateinit var mainViewModel: MainViewModel

    @get:Rule
    val rule = createComposeRule()

    @Before
    fun before() {
        mainViewModel = MainViewModel()
        mainViewModel.pastLaunchesList.value = listOf<PastLaunch>(PastLaunch(name = "Falcon"), PastLaunch(name = "Razak"))
    }

    @Test
    fun testPastLaunchesScreen() {
        rule.setContent { PastLaunchesScreen(mainViewModel) }

        rule.onNodeWithText("Falcon").assertExists()
        rule.onNodeWithText("Razak").assertExists()
        rule.onNodeWithContentDescription("Patch image")
    }
}