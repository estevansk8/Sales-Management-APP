package org.sales.management.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sales.management.clients.presentation.list.ListClientsScreen


@Composable
fun AppNavGraph(
    navController: NavHostController,
) {
//    val commonTypeMap = mapOf(typeOf<InstructorDto>() to CustomNavTypes.InstructorType)
    NavHost(
        navController = navController,
        startDestination = ListClientsScreen,
    ) {
        composable<ListClientsScreen>{
            ListClientsScreen()
        }
    }
}