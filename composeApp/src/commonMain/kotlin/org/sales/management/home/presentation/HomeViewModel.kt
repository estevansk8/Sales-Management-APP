package org.sales.management.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HomeViewModel(
    private val dataStore: DataStore<Preferences>,
) : ViewModel() {

    var userName by mutableStateOf("")
        private set

    init {
        viewModelScope.launch {
            val userKey = stringPreferencesKey("userName")
            val prefs = dataStore.data.first()
            userName = prefs[userKey] ?: "Empreendedor"
        }
    }

    fun logout() {
        viewModelScope.launch {
            val tokenKey = stringPreferencesKey("token")
            val isLoggedKey = booleanPreferencesKey("isLogged")
            val userKey = stringPreferencesKey("userName")

            dataStore.edit {
                it.remove(tokenKey)
                it.remove(userKey)
                it[isLoggedKey] = false
            }
        }
    }
}