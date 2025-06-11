package org.sales.management.products.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductRequest(
    val name: String,
    val price: String, // BackEnd como BigDecimal
    val stock: Int
)
