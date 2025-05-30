package org.sales.management.core.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.sales.management.clients.data.remote.ClientService
import org.sales.management.clients.domain.repository.ClientRepository
import org.sales.management.clients.presentation.list.ClientsListViewModel
import org.sales.management.clients.presentation.form.ClientFormsViewModel
import org.sales.management.clients.data.repositories.ClientRepositoryImpl
import org.sales.management.core.ktor.buildHttpClient
import org.sales.management.core.ktor.getHttpEngine


private val dataModule = module {
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