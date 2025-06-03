package org.sales.management.auth.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.sales.management.auth.domain.model.LoginUserRequest
import org.sales.management.auth.domain.model.LoginUserResponse
import org.sales.management.auth.domain.repository.AuthRepository

class LoginViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    var isLoading by mutableStateOf(false)
    var token by mutableStateOf("")
    var userNamer by mutableStateOf("")
    var error by mutableStateOf("")

    private suspend fun login(loginUserRequest: LoginUserRequest): LoginUserResponse? {
        return repository.login(loginUserRequest)
    }

    fun signIn(email: String, password: String) {
        viewModelScope.launch(Dispatchers.Default) {
            isLoading = true
            try {
                val request = login(email, password)
                token = request?.accessToken.toString()
                userNamer = request?.userName.toString()

            } catch (e: Exception) {
                println("Erro ao realizar login: ${e.message}")
            } finally {
                isLoading = false
            }
        }
    }
}