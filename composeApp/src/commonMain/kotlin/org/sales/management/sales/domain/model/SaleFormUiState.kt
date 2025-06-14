package org.sales.management.sales.domain.model

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import kotlinx.datetime.LocalDate
import org.sales.management.clients.domain.model.Client
import org.sales.management.products.domain.model.Product

data class SaleFormUiState(
    // Campos do formulário
    val selectedClient: Client? = null,
    val dueDate: LocalDate? = null,

    // Busca de produtos
    val searchQuery: String = "",
    val searchResults: List<Product> = emptyList(),
    val isSearching: Boolean = false,

    // Itens da venda
    val saleItems: List<SaleItem> = emptyList(),

    // Clientes para seleção
    val clientList: List<Client> = emptyList(),

    // Estado geral
    val isSubmitting: Boolean = false,
    val submissionError: String? = null,
) {
    val totalAmount: BigDecimal
        get() = saleItems.fold(BigDecimal.ZERO) { acc, item ->
            acc + item.unitPrice
        }
}
