package com.kotlin.spacexapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.util.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RocketDropdownMenuBox(mainViewModel: MainViewModel) {
    val context = LocalContext.current

    var expanded by remember { mutableStateOf(false) }
    var selectedRocketName by remember { mutableStateOf(mainViewModel.selectedFilterRocketName.value) }
    var rocketNameList = mutableListOf<String>("Any")

    mainViewModel.rocketList
    for (rocket in mainViewModel.rocketList.value) {
        rocketNameList.add(rocket.name)
    }

    Box(
        modifier = Modifier
            .width(320.dp)
            .padding(start = 30.dp, end = 30.dp, bottom = 15.dp)
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedRocketName,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                rocketNameList.forEach { name ->
                    DropdownMenuItem(
                        text = { Text(text = name) },
                        onClick = {
                            selectedRocketName = name
                            expanded = false
                            mainViewModel.selectedFilterRocketName.value = selectedRocketName
                        }
                    )
                }
            }
        }
    }
}