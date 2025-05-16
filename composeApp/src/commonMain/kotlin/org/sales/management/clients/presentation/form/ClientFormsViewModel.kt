package org.sales.management.clients.presentation.form

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.sales.management.clients.domain.model.Client
import org.sales.management.clients.domain.model.ClientDTO
import org.sales.management.clients.domain.model.ClientRequest
import org.sales.management.clients.domain.repository.ClientRepository

class ClientFormsViewModel(
    private val repository: ClientRepository
) : ViewModel() {

    var isLoading by mutableStateOf(false)

    private suspend fun createClient(client: ClientRequest): ClientDTO{
        println("Entrou saveClient")
        return repository.createClient(client)
    }

    fun saveClient( name: String, phone: String, address: String){
        viewModelScope.launch(Dispatchers.Default) {
            isLoading = true
            try {
                createClient(ClientRequest(name, phone, address))
            } catch (e: Exception) {
                println("Erro ao buscar clientes: ${e.message}")
            } finally {
                isLoading = false
            }
        }

    }

}