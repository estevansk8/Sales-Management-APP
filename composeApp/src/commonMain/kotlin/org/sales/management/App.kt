package org.sales.management

import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.sales.management.clients.data.remote.ClientService
import org.sales.management.core.ktor.buildHttpClient
import org.sales.management.core.ktor.getHttpEngine
import org.sales.management.core.navigation.AppNavGraph
import org.sales.management.core.ui.ManagementSalesTheme

val ktorEngine = ClientService(buildHttpClient(getHttpEngine()))

@Composable
@Preview
fun App() {
    ManagementSalesTheme {
        val navController = rememberNavController()

        Surface(color = MaterialTheme.colorScheme.onPrimary) {
            AppNavGraph(navController = navController)
        }
    }
}