package org.sales.management.home.presentation

import androidx.lifecycle.ViewModel
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

class HomeViewModel(
    private val dataStore: DataStore<Preferences>,
) : ViewModel() {

}