package org.sales.management.auth.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.sales.management.auth.domain.model.LoginUserRequest
import org.sales.management.auth.domain.repository.AuthRepository

class LoginViewModel(
    private val dataStore: DataStore<Preferences>,
    private val repository: AuthRepository
) : ViewModel() {

    var isLoading by mutableStateOf(false)
        private set

    var emailState by mutableStateOf("suzane2@exemplo.com")
    var passwordState by mutableStateOf("senhaForte123")

    var errorMessage by mutableStateOf<String?>(null)
        private set

    private val _loginSuccessEvent = MutableSharedFlow<Unit>()
    val loginSuccessEvent = _loginSuccessEvent.asSharedFlow()

    var token: String? by mutableStateOf(null)
        private set
    var userName: String? by mutableStateOf(null)
        private set


    fun onEmailChange(newEmail: String) {
        emailState = newEmail
        clearError()
    }

    fun onPasswordChange(newPassword: String) {
        passwordState = newPassword
        clearError()
    }

    fun clearError() {
        errorMessage = null
    }


    fun login() {
        if (emailState.isBlank() || passwordState.isBlank()) {
            errorMessage = "E-mail e senha não podem estar vazios."
            return
        }

        viewModelScope.launch(Dispatchers.Default) {
            isLoading = true
            errorMessage = null

            try {
                val response = repository.login(LoginUserRequest(emailState, passwordState))

                if (response.success && response.data != null) {
                    token = response.data.accessToken
                    userName = response.data.username

                    dataStore.edit {
                        it[booleanPreferencesKey("isLogged")] = true
                        it[stringPreferencesKey("token")] = token!!
                        it[stringPreferencesKey("username")] = userName!!
                    }

                    _loginSuccessEvent.emit(Unit)
                } else {
                    errorMessage = response.message.takeIf { it.isNotBlank() } ?: "Erro desconhecido."
                }
            } catch (e: Exception) {
                println("LoginViewModel - Erro ao realizar login: ${e.message}")
                errorMessage = "Ocorreu um erro inesperado. Tente novamente."
            } finally {
                isLoading = false
            }
        }
    }
}