package org.sales.management.sales.domain.model

import kotlinx.serialization.Serializable
import org.sales.management.products.domain.model.ProductRequest

@Serializable
data class SaleRequest(
    val date: String,
    val clientId: Long,
    val products: List<ProductRequest>
)
