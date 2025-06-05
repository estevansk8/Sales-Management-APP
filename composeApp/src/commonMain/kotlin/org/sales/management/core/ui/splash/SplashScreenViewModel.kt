package org.sales.management.core.ui.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
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
            val isLoggedKey = booleanPreferencesKey("isLogged")
            val tokenKey = stringPreferencesKey("token")
            val userKey = stringPreferencesKey("username")

            val prefs = dataStore.data.first()

            val isLoggedVal = prefs[isLoggedKey] ?: false
            val token = prefs[tokenKey]
            val user = prefs[userKey]

            println("🌐 isLogged = $isLoggedVal")
            println("🔑 token = $token")
            println("👤 userName = $user")

            val isLogged = booleanPreferencesKey("isLogged")
            isLoggedIn = prefs[isLogged] ?: false
        }
    }
}