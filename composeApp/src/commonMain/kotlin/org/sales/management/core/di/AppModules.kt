package org.sales.management.core.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.sales.management.auth.data.remote.AuthService
import org.sales.management.auth.data.repository.AuthRepositoryImpl
import org.sales.management.auth.domain.repository.AuthRepository
import org.sales.management.auth.presentation.login.LoginViewModel
import org.sales.management.clients.data.remote.ClientService
import org.sales.management.clients.domain.repository.ClientRepository
import org.sales.management.clients.presentation.list.ClientsListViewModel
import org.sales.management.clients.presentation.form.ClientFormsViewModel
import org.sales.management.clients.data.repositories.ClientRepositoryImpl
import org.sales.management.core.data.remote.ktor.buildHttpClient
import org.sales.management.core.data.remote.ktor.getHttpEngine
import org.sales.management.core.ui.splash.SplashScreenViewModel


private val dataModule = module {
    single<AuthService>{
        AuthService(
            httpClient = buildHttpClient(getHttpEngine())
        )
    }

    single<AuthRepository> {
        AuthRepositoryImpl(
            service = get()
        )
    }

    single<ClientService> {
        ClientService(
            httpClient = buildHttpClient(getHttpEngine())
        )
    }

    single<ClientRepository> {
        ClientRepositoryImpl(
            service = get()
        )
    }
}

private val viewModelModule = module {
    viewModel {
        SplashScreenViewModel(get())
    }

    viewModel{
        LoginViewModel(
            repository = get()
        )
    }

    viewModel {
        ClientsListViewModel(
            repository = get()
        )
    }

    viewModel {
        ClientFormsViewModel(
            repository = get()
        )
    }
}

val appModules = listOf(
    dataModule, viewModelModule
)