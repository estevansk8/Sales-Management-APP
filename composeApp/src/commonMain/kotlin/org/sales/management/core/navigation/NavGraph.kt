package org.sales.management.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sales.management.clients.presentation.list.ClientsListScreen
import org.sales.management.clients.presentation.form.ClientFormsScreen
import org.sales.management.home.presentation.HomeScreen


@Composable
fun AppNavGraph(
    navController: NavHostController,
) {
//    val commonTypeMap = mapOf(typeOf<InstructorDto>() to CustomNavTypes.InstructorType)
    NavHost(
        navController = navController,
        startDestination = HomeScreen,
    ) {
        composable<HomeScreen>{
            HomeScreen(
                goToClients = {
                    navController.navigate(ClientsListScreen)
                }
            )
        }
        composable<ClientsListScreen>{
            ClientsListScreen(
                goToClientForm = {
                    navController.navigate(ClientsFormScreen)
                }
            )
        }
        composable<ClientsFormScreen>{
            ClientFormsScreen()
        }
    }
}