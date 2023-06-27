package com.kotlin.spacexapp

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
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
fun FromYearDropdownMenuBox(mainViewModel: MainViewModel) {
    val context = LocalContext.current
    val years = mutableListOf<Int>()

    // Get current year to know up to when add the years
    val calendar: Calendar = Calendar.getInstance()
    val year: Int = calendar.get(Calendar.YEAR)
    for (num in 2006..year) {
        years.add(num)
    }
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(mainViewModel.selectedTextFrom.value) }

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
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                years.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item.toString()) },
                        onClick = {
                            selectedText = item.toString()
                            expanded = false
                            mainViewModel.selectedTextFrom.value = selectedText

                        }
                    )
                }
            }
        }
    }
}