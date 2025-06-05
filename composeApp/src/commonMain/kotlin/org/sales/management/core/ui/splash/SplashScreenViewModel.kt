package org.sales.management.core.ui.splash

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import org.sales.management.clients.domain.repository.ClientRepository

class SplashScreenViewModel(
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

}