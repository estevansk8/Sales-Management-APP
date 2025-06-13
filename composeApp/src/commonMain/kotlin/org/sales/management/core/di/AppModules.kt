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
import org.sales.management.core.data.local.datastore.preferenceModule
import org.sales.management.core.data.remote.ktor.buildHttpClient
import org.sales.management.core.data.remote.ktor.getHttpEngine
import org.sales.management.core.ui.splash.SplashScreenViewModel
import org.sales.management.home.presentation.HomeViewModel
import org.sales.management.products.data.remote.ProductService
import org.sales.management.products.data.repositories.ProductMockRepositoryImpl
import org.sales.management.products.domain.repository.ProductRepository
import org.sales.management.products.presentation.form.ProductFormsViewModel
import org.sales.management.products.presentation.list.ProductsListViewModel


private val dataModule = module {
    single<AuthService>{
        AuthService(
            httpClient = buildHttpClient(
                dataStore = get(),
                engine = getHttpEngine()
            )
        )
    }

    single<AuthRepository> {
        AuthRepositoryImpl(
            service = get()
        )
    }

    single<ClientService> {
        ClientService(
            httpClient = buildHttpClient(
                dataStore = get(),
                engine = getHttpEngine()
            )
        )
    }

    single<ClientRepository> {
        ClientRepositoryImpl(
            service = get()
        )
    }

    single<ProductService> {
        ProductService(
            httpClient = buildHttpClient(
                dataStore = get(),
                engine = getHttpEngine()
            )
        )
    }

    single<ProductRepository> {
        ProductMockRepositoryImpl(
            service = get()
        )
    }

}

private val viewModelModule = module {
    viewModel {
        SplashScreenViewModel(
            dataStore = get()
        )
    }

    viewModel{
        LoginViewModel(
            dataStore = get(),
            repository = get()
        )
    }

    viewModel {
        HomeViewModel(
            dataStore = get()
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

    viewModel {
        ProductsListViewModel(
            repository = get()
        )
    }

    viewModel {
        ProductFormsViewModel(
            repository = get()
        )
    }
}

val appModules = listOf(
    dataModule, viewModelModule, preferenceModule
)