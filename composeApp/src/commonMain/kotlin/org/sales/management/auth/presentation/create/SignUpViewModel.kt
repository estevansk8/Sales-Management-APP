package org.sales.management.auth.presentation.create

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.sales.management.auth.domain.model.create.SignUpUserRequest
import org.sales.management.auth.domain.model.create.SignUpUserResponse
import org.sales.management.auth.domain.repository.AuthRepository
import org.sales.management.core.ui.SnackbarEvent

class SignUpViewModel(
    private val repository: AuthRepository
) : ViewModel() {
    var isLoading by mutableStateOf(false)

    private val _eventFlow = MutableSharedFlow<SnackbarEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private suspend fun signUpUser(signUpUser: SignUpUserRequest): SignUpUserResponse {
        return repository.signUp(signUpUser)
    }

    fun signUp( name: String, email: String, password: String, passwordConfirm: String){

        if (password != passwordConfirm || password.length < 6 ) {
            viewModelScope.launch {
                _eventFlow.emit(SnackbarEvent("As senhas não coincidem!", true))
            }
            return
        }

        viewModelScope.launch(Dispatchers.Default) {
            isLoading = true
            try {
                signUpUser(SignUpUserRequest(name, email, password ))
                _eventFlow.emit(SnackbarEvent("Conta criada com sucesso!", false))
            } catch (e: Exception) {
                println("Erro ao buscar produtos: ${e.message}")
                _eventFlow.emit(SnackbarEvent("Erro ao criar conta: ${e.message}", true))
            } finally {
                isLoading = false
            }
        }

    }
}