package org.sales.management.auth.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.sales.management.auth.domain.repository.AuthRepository

class LoginViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    var isLoading by mutableStateOf(false)
    var token by mutableStateOf("")
    var userNamer by mutableStateOf("")
    var error by mutableStateOf("")

    suspend fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.Default) {
            isLoading = true
            try {
                var request = repository.login(email, password)
            } catch (e: Exception) {
                println("Erro ao realizar login: ${e.message}")
            } finally {
                isLoading = false
            }
        }
    }
}