package org.sales.management

import org.koin.core.context.startKoin
import org.sales.management.core.di.appModules

fun initKoin() {
    startKoin {
        modules(appModules)
    }
}