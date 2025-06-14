package org.sales.management.sales.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SaleItemRequestDTO(
    val productId: Long,
    val quantity: Int,
    val unitPrice: String //String por conta do BigDecimal
)
