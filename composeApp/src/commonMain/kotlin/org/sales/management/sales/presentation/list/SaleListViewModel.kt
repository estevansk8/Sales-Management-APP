package org.sales.management.sales.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.sales.management.core.ui.SnackbarEvent
import org.sales.management.sales.domain.model.sale.SaleRequestDTO
import org.sales.management.sales.domain.model.sale.SaleResponse
import org.sales.management.sales.domain.model.sale.SaleStatus
import org.sales.management.sales.domain.model.saleitem.SaleItemRequestDTO
import org.sales.management.sales.domain.repository.SaleRepository

class SaleListViewModel(
    private val repository: SaleRepository
) : ViewModel() {

    private val _salesState = MutableStateFlow<List<SaleResponse>>(emptyList())
    val salesState: StateFlow<List<SaleResponse>> = _salesState

    private val _eventFlow = MutableSharedFlow<SnackbarEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    init { loadSales() }

    fun loadSales() {
        viewModelScope.launch {
            repository.getSales().collect { res ->
                res.onSuccess { _salesState.value = it }
                    .onFailure { _eventFlow.emit(SnackbarEvent("Erro: ${it.message}", true)) }
            }
        }
    }

    fun updateStatus(sale: SaleResponse, newStatus: SaleStatus) {
        viewModelScope.launch {
            val req = SaleRequestDTO(
                clientId = sale.clientId,
                saleDate = sale.saleDate,
                status = newStatus,
                dueDate = sale.dueDate,
                items = sale.items.map { item ->
                    SaleItemRequestDTO(item.productId, item.quantity, item.unitPrice)
                }
            )
            repository.updateSale(req).collect { result ->
                result.onSuccess {
                    _eventFlow.emit(SnackbarEvent("Status atualizado", false))
                    loadSales()
                }.onFailure {
                    _eventFlow.emit(SnackbarEvent("Erro ao atualizar: ${it.message}", true))
                }
            }
        }
    }
}
