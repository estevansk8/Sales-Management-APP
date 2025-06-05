package org.sales.management.core.data.local.datastore

import org.koin.core.module.Module
import org.koin.dsl.module

actual val preferenceModule: Module = module {
    single { createDataStore() }
}