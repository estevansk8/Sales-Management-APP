package org.sales.management.core.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.sales.management.auth.data.remote.AuthService
import org.sales.management.auth.data.repository.AuthRepositoryImpl
import org.sales.management.auth.domain.repository.AuthRepository
import org.sales.management.auth.presentation.create.SignUpViewModel
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
import org.sales.management.products.data.repositories.ProductRepositoryImpl
import org.sales.management.products.domain.repository.ProductRepository
import org.sales.management.products.presentation.form.ProductFormsViewModel
import org.sales.management.products.presentation.list.ProductsListViewModel
import org.sales.management.sales.data.remote.SaleService
import org.sales.management.sales.data.repositories.SaleRepositoryImpl
import org.sales.management.sales.domain.repository.SaleRepository
import org.sales.management.sales.presentation.form.SaleFormsViewModel
import org.sales.management.sales.presentation.list.SaleListViewModel


private val dataModule = module {
    factory<AuthService>{
        AuthService(
            httpClient = buildHttpClient(
                dataStore = get(),
                engine = getHttpEngine()
            )
        )
    }

    factory<AuthRepository> {
        AuthRepositoryImpl(
            service = get()
        )
    }

    factory<ClientService> {
        ClientService(
            httpClient = buildHttpClient(
                dataStore = get(),
                engine = getHttpEngine()
            )
        )
    }

    factory<ClientRepository> {
        ClientRepositoryImpl(
            service = get()
        )
    }

    factory<ProductService> {
        ProductService(
            httpClient = buildHttpClient(
                dataStore = get(),
                engine = getHttpEngine()
            )
        )
    }

    factory<ProductRepository> {
        ProductRepositoryImpl(
            service = get()
        )
    }

    factory<SaleRepository> {
        SaleRepositoryImpl(
            saleService = get()
        )
    }

    factory<SaleService> {
        SaleService(
            httpClient = buildHttpClient(
                dataStore = get(),
                engine = getHttpEngine()
            )
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
        SignUpViewModel(
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

    viewModel {
        SaleFormsViewModel(
            saleRepository = get(),
            clientRepository = get(),
            productRepository = get()
        )
    }

    viewModel {
        SaleListViewModel(
            repository = get()
        )
    }

}

val appModules = listOf(
    dataModule, viewModelModule, preferenceModule
)