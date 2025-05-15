package org.sales.management

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.sales.management.core.di.appModules

class BaseApp : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@BaseApp)
            modules(appModules)
        }
    }
}