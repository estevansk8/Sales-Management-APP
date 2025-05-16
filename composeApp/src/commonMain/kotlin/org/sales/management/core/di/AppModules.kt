package org.sales.management.core.di

import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import org.sales.management.clients.presentation.list.ListClientsViewModel

val viewModelModule = module {
    viewModel {
        ListClientsViewModel(
            repository = get()
        )
    }
}

val appModules = listOf<Module>()