package org.sales.management.settings.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.sales.management.core.ui.composables.TopBar
import org.sales.management.settings.data.repositories.SettingMockRepositoryImpl
import org.sales.management.settings.presentation.SettingItemCard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.unit.dp
import org.sales.management.core.ui.composables.BottomBar


@Composable
fun SettingsScreen(
    onTabSelected: (Int) -> Unit,
) {

    val mockSettings = SettingMockRepositoryImpl().mockSettings
    Scaffold(
        topBar = {
            TopBar(title = "Configurações"){}
        },
        bottomBar = {
            BottomBar(
                selectedIndex = 2,
                onItemSelected = {
                    onTabSelected(it)
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(mockSettings) { setting ->
                SettingItemCard(setting)
            }
        }
    }
}
