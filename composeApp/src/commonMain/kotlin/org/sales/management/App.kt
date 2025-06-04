package org.sales.management

import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.sales.management.core.navigation.NavHost
import org.sales.management.core.ui.ManagementSalesTheme

@Composable
@Preview
fun App() {
    ManagementSalesTheme {
        val navController = rememberNavController()

        Surface(color = MaterialTheme.colorScheme.onPrimary) {
            NavHost(navController = navController)
        }
    }
}