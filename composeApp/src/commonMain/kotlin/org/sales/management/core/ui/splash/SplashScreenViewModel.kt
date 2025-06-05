package org.sales.management.core.ui.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.sales.management.clients.domain.repository.ClientRepository

class SplashScreenViewModel(
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    var isLoggedIn: Boolean? by mutableStateOf(null)
        private set

    init {
        viewModelScope.launch {
            val isLogged = booleanPreferencesKey("isLogged")
            val prefs = dataStore.data.first()
            isLoggedIn = prefs[isLogged] ?: false
        }
    }
}