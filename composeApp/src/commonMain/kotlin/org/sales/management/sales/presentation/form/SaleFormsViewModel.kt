package org.sales.management.sales.presentation.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ionspin.kotlin.bignum.decimal.toBigDecimal
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.sales.management.clients.domain.model.Client
import org.sales.management.clients.domain.repository.ClientRepository
import org.sales.management.core.ui.SnackbarEvent
import org.sales.management.products.domain.model.Product
import org.sales.management.products.domain.repository.ProductRepository
import org.sales.management.sales.domain.model.SaleFormUiState
import org.sales.management.sales.domain.model.sale.SaleRequestDTO
import org.sales.management.sales.domain.repository.SaleRepository
import org.sales.management.sales.domain.model.saleitem.SaleItem
import org.sales.management.sales.domain.model.saleitem.SaleItemRequestDTO
import org.sales.management.sales.domain.model.sale.SaleStatus

class SaleFormsViewModel(
    private val saleRepository: SaleRepository,
    private val productRepository: ProductRepository,
    private val clientRepository: ClientRepository
) : ViewModel() {

    private val _eventFlow = MutableSharedFlow<SnackbarEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _uiState = MutableStateFlow(SaleFormUiState())
    val uiState: StateFlow<SaleFormUiState> = _uiState.asStateFlow()

    init {
        loadClients()
    }

    private fun loadClients() {
        viewModelScope.launch {
            val clients = clientRepository.getAllClients()
            if (clients != null)
                _uiState.update { it.copy(clientList = clients) }
        }
    }

    fun onSearchQueryChange(query: String) {
        _uiState.update { it.copy(searchQuery = query, isSearching = true) }

        viewModelScope.launch {
            val results = productRepository.getProductsByName(query)
            if (results != null)
                _uiState.update { it.copy(searchResults = results, isSearching = false) }
        }
    }

    fun onProductSelected(product: Product) {
        val existingItem = _uiState.value.saleItems.find { it.productId == product.id }

        val updatedItems = if (existingItem != null) {
            _uiState.value.saleItems.map {
                if (it.productId == product.id) it.copy(quantity = it.quantity + 1) else it
            }
        } else {
            _uiState.value.saleItems + SaleItem(
                productId = product.id,
                name = product.name,
                unitPrice = product.price.toBigDecimal()
            )
        }

        _uiState.update {
            it.copy(
                saleItems = updatedItems,
                searchQuery = "",
                searchResults = emptyList()
            )
        }
    }

    fun onQuantityChange(productId: Long, change: Int) {
        val updatedItems = _uiState.value.saleItems.mapNotNull {
            if (it.productId == productId) {
                val newQuantity = (it.quantity + change).coerceAtLeast(1)
                it.copy(quantity = newQuantity)
            } else it
        }
        _uiState.update { it.copy(saleItems = updatedItems) }
    }

    fun onClientSelected(client: Client) {
        _uiState.update { it.copy(selectedClient = client) }
    }

    fun onDueDateSelected(date: LocalDate) {
        _uiState.update { it.copy(dueDate = date) }
    }

    fun onSubmitSale() {
        val currentState = _uiState.value
        if (currentState.selectedClient == null || currentState.saleItems.isEmpty()){
            viewModelScope.launch {
                _eventFlow.emit(SnackbarEvent("Cliente e itens são obrigatórios", true))
            }

            return
        }

        _uiState.update { it.copy(isSubmitting = true) }

        viewModelScope.launch {

            val saleRequest = SaleRequestDTO(
                clientId = currentState.selectedClient.id.toLong(),
                saleDate = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date,
                status = SaleStatus.PENDING,
                dueDate = currentState.dueDate,
                items = currentState.saleItems.map {
                    SaleItemRequestDTO(
                        productId = it.productId,
                        quantity = it.quantity,
                        unitPrice = it.unitPrice.toPlainString()
                    )
                }
            )

            viewModelScope.launch {
                saleRepository.createSale(saleRequest).collect { result ->
                    result.onSuccess {
                        _eventFlow.emit(SnackbarEvent("Venda criada com sucesso", false))
                        _uiState.update { SaleFormUiState() }
                    }.onFailure { e ->
                        _eventFlow.emit(SnackbarEvent("Erro: ${e.message}", true))
                        _uiState.update { it.copy(isSubmitting = false) }
                    }
                }
            }
        }
    }
}
