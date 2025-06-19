package org.sales.management.core.ui.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import managementsalesapp.composeapp.generated.resources.Res
import managementsalesapp.composeapp.generated.resources.compose_multiplatform
import managementsalesapp.composeapp.generated.resources.ic_splash_screen
import managementsalesapp.composeapp.generated.resources.img
import managementsalesapp.composeapp.generated.resources.login
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.sales.management.clients.presentation.list.ClientsListViewModel

@Composable
fun SplashScreen(
    goToLogin: () -> Unit,
    goToHome: () -> Unit,
    viewModel: SplashScreenViewModel = koinViewModel()
) {

    val isLoggedIn = viewModel.isLoggedIn

    LaunchedEffect(isLoggedIn) {
        when (isLoggedIn) {
            true -> goToHome()
            false -> goToLogin()
            null -> {}
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(Res.drawable.login),
            contentDescription = null,
            modifier = Modifier.size(280.dp)
        )
    }

}