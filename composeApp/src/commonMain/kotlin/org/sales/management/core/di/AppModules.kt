package org.sales.management.core.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.sales.management.clients.data.remote.ClientService
import org.sales.management.clients.domain.repository.ClientRepository
import org.sales.management.clients.presentation.list.ListClientsViewModel
import org.sales.management.clients.data.repositories.ClientRepositoryImpl
import org.sales.management.core.ktor.buildHttpClient
import org.sales.management.core.ktor.getHttpEngine


val dataModule = module {
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

val viewModelModule = module {
    viewModel {
        ListClientsViewModel(
            repository = get()
        )
    }
}

val appModules = listOf<Module>()