package org.sales.management.core.ui.splash

import androidx.compose.runtime.Composable
import org.koin.compose.viewmodel.koinViewModel
import org.sales.management.clients.presentation.list.ClientsListViewModel

@Composable
fun ClientsListScreen(
    goToLogin: () -> Unit,
    goToHome: () -> Unit,
    viewModel: SplashScreenViewModel = koinViewModel()
) {

}