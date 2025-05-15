package org.sales.management

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.sales.management.clients.data.remote.ClientsApi
import org.sales.management.clients.presentation.list.ListClientsScreen
import org.sales.management.core.ktor.buildHttpClient
import org.sales.management.core.ktor.getHttpEngine
import org.sales.management.core.navigation.AppNavGraph
import org.sales.management.core.ui.ManagementSalesTheme

val ktorEngine = ClientsApi(buildHttpClient(getHttpEngine()))

@Composable
@Preview
fun App() {
    ManagementSalesTheme {
        val navController = rememberNavController()

        ListClientsScreen(
            ktorEngine
        )
//        Surface(color = MaterialTheme.colorScheme.onPrimary) {
//            AppNavGraph(navController = navController)
//        }
    }
}