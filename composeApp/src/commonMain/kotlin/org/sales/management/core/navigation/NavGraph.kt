package org.sales.management.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sales.management.clients.presentation.list.ClientsListScreen
import org.sales.management.clients.presentation.form.ClientFormsScreen


@Composable
fun AppNavGraph(
    navController: NavHostController,
) {
//    val commonTypeMap = mapOf(typeOf<InstructorDto>() to CustomNavTypes.InstructorType)
    NavHost(
        navController = navController,
        startDestination = ClientsListScreen,
    ) {
        composable<ClientsListScreen>{
            ClientsListScreen()
        }
        composable<ClientsFormScreen>{
            ClientFormsScreen()
        }
    }
}