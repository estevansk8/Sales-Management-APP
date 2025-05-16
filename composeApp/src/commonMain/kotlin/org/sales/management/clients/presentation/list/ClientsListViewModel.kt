package org.sales.management.clients.presentation.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.sales.management.clients.domain.repository.ClientRepository
import org.sales.management.clients.domain.model.Client

class ClientsListViewModel(
    private val repository: ClientRepository
) : ViewModel() {

    var clients = mutableStateListOf<Client>()
    var isLoading by mutableStateOf(false)

    private suspend fun getClients(): List<Client>? {
        println("Entrou getClients")
        return repository.getAllClients()
    }

    fun listClients(){
        viewModelScope.launch(Dispatchers.Default) {
            isLoading = true
            try {
                println("Entrou listClients")
                val result = getClients()
                clients.clear()
                clients.addAll(result ?: emptyList())
            } catch (e: Exception) {
                println("Erro ao buscar clientes: ${e.message}")
            } finally {
                isLoading = false
            }
        }

    }

    private suspend fun deleteClient(id: Long){
        repository.deleteClient(id)
    }

    fun removeClient(id: Long){
        viewModelScope.launch(Dispatchers.Default) {
            isLoading = true
            try {
                deleteClient(id)
            } catch (e: Exception) {
                println("Erro ao buscar clientes: ${e.message}")
                } finally {
                isLoading = false
            }
        }
    }

}
