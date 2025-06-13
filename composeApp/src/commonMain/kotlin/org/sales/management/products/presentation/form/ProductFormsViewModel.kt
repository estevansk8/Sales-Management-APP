package org.sales.management.products.presentation.form

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
import org.sales.management.core.ui.SnackbarEvent
import org.sales.management.products.domain.model.Product
import org.sales.management.products.domain.model.ProductRequest
import org.sales.management.products.domain.repository.ProductRepository

class ProductFormsViewModel(
    private val repository: ProductRepository
) : ViewModel() {
    var isLoading by mutableStateOf(false)

    private val _eventFlow = MutableSharedFlow<SnackbarEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private suspend fun createProduct(product: ProductRequest): Product {
        return repository.createProduct(product)
    }

    fun saveProduct( name: String, price: String, stock: Int){
        viewModelScope.launch(Dispatchers.Default) {
            isLoading = true
            try {
                createProduct(ProductRequest(name, price, stock))
                _eventFlow.emit(SnackbarEvent("Produto criado com sucesso!", false))
            } catch (e: Exception) {
                println("Erro ao buscar produtos: ${e.message}")
                _eventFlow.emit(SnackbarEvent("Erro ao criar produto: ${e.message}", true))
            } finally {
                isLoading = false
            }
        }

    }
}