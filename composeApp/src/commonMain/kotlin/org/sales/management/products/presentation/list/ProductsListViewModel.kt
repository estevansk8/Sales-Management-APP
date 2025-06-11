package org.sales.management.products.presentation.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.sales.management.clients.domain.model.Client
import org.sales.management.products.domain.model.Product
import org.sales.management.products.domain.repository.ProductRepository

class ProductsListViewModel(
    private val repository: ProductRepository
): ViewModel() {
    var products = mutableStateListOf<Product>()
    var isLoading by mutableStateOf(false)

    private suspend fun getProducts(): List<Product>? {
        return repository.getAllProducts()
    }

    fun listProducts(){
        viewModelScope.launch(Dispatchers.Default) {
            isLoading = true
            try {
                val result = getProducts()
                products.clear()
                products.addAll(result ?: emptyList())
            } catch (e: Exception) {
                println("Erro ao buscar produtos: ${e.message}")
            } finally {
                isLoading = false
            }
        }

    }

    private suspend fun deleteProduct(id: Long){
        repository.deleteProduct(id)
    }

    fun removeProduct(id: Long){
        viewModelScope.launch(Dispatchers.Default) {
            isLoading = true
            try {
                deleteProduct(id)
            } catch (e: Exception) {
                println("Erro ao buscar produto: ${e.message}")
            } finally {
                isLoading = false
            }
        }
    }
}

