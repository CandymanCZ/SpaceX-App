package com.kotlin.spacexapp

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kotlin.spacexapp.ui.theme.SpaceGrey

@Composable
fun SpinnyProgressBar(isDisplayed: Boolean) {
    if (isDisplayed) {
        CircularProgressIndicator(color = MaterialTheme.colors.onPrimary, modifier = Modifier.size(20.dp))
    }
}