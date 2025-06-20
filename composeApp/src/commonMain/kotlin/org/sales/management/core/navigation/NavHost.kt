package org.sales.management.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sales.management.auth.presentation.login.LoginScreen
import org.sales.management.auth.presentation.create.SignUpScreen
import org.sales.management.clients.presentation.list.ClientsListScreen
import org.sales.management.clients.presentation.form.ClientFormsScreen
import org.sales.management.core.ui.splash.SplashScreen
import org.sales.management.home.presentation.HomeScreen
import org.sales.management.products.presentation.form.ProductFormsScreen
import org.sales.management.products.presentation.list.ProductsListScreen
import org.sales.management.reports.presentation.ReportsScreen
import org.sales.management.sales.presentation.form.SaleFormScreen
import org.sales.management.sales.presentation.list.SaleListScreen


@Composable
fun NavHost(
    navController: NavHostController,
) {
//    val commonTypeMap = mapOf(typeOf<InstructorDto>() to CustomNavTypes.InstructorType)
    NavHost(
        navController = navController,
        startDestination = SplashScreen,
    ) {

        composable<SplashScreen>{
            SplashScreen(
                goToLogin = {
                    navController.navigate(LoginScreen)
                },
                goToHome = {
                    navController.navigate(HomeScreen)
                }
            )
        }
        composable<LoginScreen>{
            LoginScreen(
                onLogin = {
                    navController.navigate(HomeScreen)
                },
                onSignUp = {
                    navController.navigate(SignUpScreen)
                }
            )
        }
        composable<SignUpScreen>{
            SignUpScreen(
                onBack = {
                    navController.navigate(LoginScreen)
                }
            )
        }
        composable<HomeScreen>{
            HomeScreen(
                goMakeSale = {
                    navController.navigate(SaleFormScreen)
                },
                goToClients = {
                    navController.navigate(ClientsListScreen)
                },
                goToProducts = {
                    navController.navigate(ProductsListScreen)
                },
                goToSales = {
                    navController.navigate(SaleListScreen)
                },
                onTabSelected = { index ->
                    when (index) {
                        0 -> navController.navigate(HomeScreen)
                        1 -> navController.navigate(ReportsScreen)
                        2 -> navController.navigate(SettingsScreen)
                        else -> {}
                    }
                },
                onExit = {
                    navController.navigate(LoginScreen){
                        popUpTo(LoginScreen) { inclusive = true }
                    }
                }
            )
        }
        composable<ReportsScreen>{
            ReportsScreen(
                onTabSelected = { index ->
                    when (index) {
                        0 -> navController.navigate(HomeScreen)
                        1 -> navController.navigate(ReportsScreen)
                        2 -> navController.navigate(SettingsScreen)
                        else -> {}
                    }
                }
            )
        }
        composable<ClientsListScreen>{
            ClientsListScreen(
                goToClientForm = {
                    navController.navigate(ClientsFormScreen)
                },
                goBack = {
                    navController.popBackStack()
                }
            )
        }
        composable<ClientsFormScreen>{
            ClientFormsScreen(
                goBack = {
                    navController.popBackStack()
                }
            )
        }
        composable<ProductsListScreen>{
            ProductsListScreen(
                goToProductForm = {
                    navController.navigate(ProductsFormScreen)
                },
                goBack = {
                    navController.popBackStack()
                }
            )
        }
        composable<ProductsFormScreen>{
            ProductFormsScreen(
                goBack = {
                    navController.popBackStack()
                }
            )
        }
        composable<SaleFormScreen>{
            SaleFormScreen(
                goBack = {
                    navController.popBackStack()
                }
            )
        }
        composable<SaleListScreen>{
            SaleListScreen(
                goToDetail = {},
                goBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}