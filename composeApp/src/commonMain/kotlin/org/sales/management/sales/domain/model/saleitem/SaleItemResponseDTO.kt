package org.sales.management.sales.domain.model.saleitem

import kotlinx.serialization.Serializable

@Serializable
data class SaleItemResponseDTO(
    val productId: Long,
    val productName: String,
    val quantity: Int,
    val unitPrice: String,
    val subtotal: String
)
