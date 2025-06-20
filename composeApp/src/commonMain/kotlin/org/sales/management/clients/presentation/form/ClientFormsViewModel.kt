package org.sales.management.clients.presentation.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.sales.management.clients.domain.model.ClientDTO
import org.sales.management.clients.domain.model.ClientRequest
import org.sales.management.clients.domain.repository.ClientRepository
import org.sales.management.core.ui.SnackbarEvent

class ClientFormsViewModel(
    private val repository: ClientRepository
) : ViewModel() {

    var isLoading by mutableStateOf(false)

    private val _eventFlow = MutableSharedFlow<SnackbarEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private suspend fun createClient(client: ClientRequest): ClientDTO{
        println("Entrou saveClient")
        return repository.createClient(client)
    }

    fun saveClient( name: String, phone: String, address: String){
        viewModelScope.launch(Dispatchers.Default) {
            isLoading = true
            try {
                createClient(ClientRequest(name, phone, address))
                _eventFlow.emit(SnackbarEvent("Cliente criado com sucesso!", false))
            } catch (e: Exception) {
                println(e)
                _eventFlow.emit(SnackbarEvent("Erro ao criar cliente: ${e.message}", true))
            } finally {
                isLoading = false
            }
        }

    }

}