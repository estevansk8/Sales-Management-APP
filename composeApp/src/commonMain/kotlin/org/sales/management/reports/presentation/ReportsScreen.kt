package org.sales.management.reports.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sales.management.core.ui.composables.BottomBar
import org.sales.management.core.ui.composables.TopBar
import org.sales.management.reports.data.repositories.ReportsRepositoryMockImpl

@Composable
fun ReportsScreen(
    onTabSelected: (Int) -> Unit
) {

    var selectedTab by remember { mutableStateOf(1) }
    val mockReportData = ReportsRepositoryMockImpl().mockReportData

    Scaffold(
        topBar = {
            TopBar(title = "Relatórios"){}
        },
        bottomBar = {
            BottomBar(
                selectedIndex = selectedTab,
                onItemSelected = {
                    selectedTab = it
                    onTabSelected(it)
                }
            )
        },
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(mockReportData) { data ->
                ReportCard(data)
            }
        }
    }
}
