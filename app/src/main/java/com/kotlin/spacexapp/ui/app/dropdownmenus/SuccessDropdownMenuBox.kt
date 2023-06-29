package com.kotlin.spacexapp.ui.app.dropdownmenus

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.kotlin.spacexapp.viewmodels.MainViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuccessDropdownMenuBox(mainViewModel: MainViewModel) {
    val context = LocalContext.current

    var expanded by remember { mutableStateOf(false) }
    var selectedSuccessOption by remember { mutableStateOf(mainViewModel.selectedFlightSuccessOption.value) }
    var selectOptions = mutableListOf<String>("Any", "Successful", "Failed")

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
                value = selectedSuccessOption,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                selectOptions.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(text = option) },
                        onClick = {
                            selectedSuccessOption = option
                            expanded = false
                            mainViewModel.selectedFlightSuccessOption.value = selectedSuccessOption
                        }
                    )
                }
            }
        }
    }
}