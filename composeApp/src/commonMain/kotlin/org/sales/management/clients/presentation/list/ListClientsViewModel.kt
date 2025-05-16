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

class ListClientsViewModel(
    private val repository: ClientRepository
) : ViewModel() {

    var clients = mutableStateListOf<Client>()
    var isLoading by mutableStateOf(false)

    suspend fun getClients(): List<Client>? {
        return repository.getAllClients()
    }

    fun listClients(){
        isLoading = true
        try {
            viewModelScope.launch(Dispatchers.Default) {
                clients.addAll(getClients() ?: emptyList())
            }
        }catch (e: Exception){
            println(e.message)
        }finally {
            isLoading = false
        }

    }

}
