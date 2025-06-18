package org.sales.management.sales.domain.model.saleitem

import kotlinx.serialization.Serializable

@Serializable
data class SaleItemResponseDTO(
    val productId: Long,
    val name: String,
    val unitPrice: String,
    val quantity: Int,
    val subtotal: String
)
