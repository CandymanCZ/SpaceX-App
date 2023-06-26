package com.kotlin.spacexapp

import android.view.Surface
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CompanyInfoScreen(mainViewModel: MainViewModel) {
    val context = LocalContext.current
    mainViewModel.fetchCompanyInfo()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(15.dp),
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 20.dp),
                text = mainViewModel.companyInfo.value.name,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textDecoration = TextDecoration.Underline,
                fontSize = 35.sp
            )
        }
        Text(text = mainViewModel.companyInfo.value.summary)
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 20.dp),
            text = "Headquarters",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline,
            fontSize = 25.sp
        )
        Text(text = "State: " + mainViewModel.companyInfo.value.headquarters.state)
        Text(text = "City: " + mainViewModel.companyInfo.value.headquarters.city)
        Text(text = "Address: " + mainViewModel.companyInfo.value.headquarters.address)

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 20.dp),
            text = "Information",
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            textDecoration = TextDecoration.Underline,
            fontSize = 25.sp
        )
        Text(text = "Founder: " + mainViewModel.companyInfo.value.founder)
        Text(text = "Founded in: " + mainViewModel.companyInfo.value.founded)
        Text(text = "Number of employees: " + mainViewModel.companyInfo.value.employees)
        Text(text = "Number of vehicles: " + mainViewModel.companyInfo.value.vehicles)
        Text(text = "Number of launch sites: " + mainViewModel.companyInfo.value.launchSites)
        Text(text = "Number of test sites: " + mainViewModel.companyInfo.value.testSites)
        Text(text = "Number of launch sites: " + mainViewModel.companyInfo.value.launchSites)
        Text(text = "CEO: " + mainViewModel.companyInfo.value.ceo)
        Text(text = "CTO: " + mainViewModel.companyInfo.value.cto)
        Text(text = "COO: " + mainViewModel.companyInfo.value.coo)
        Text(text = "CTO of propulsion: " + mainViewModel.companyInfo.value.ctoPropulsion)
        Text(text = "Valuation: " + mainViewModel.companyInfo.value.valuation + " USD")
    }
}