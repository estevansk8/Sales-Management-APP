package org.sales.management.sales.domain.model

import kotlinx.serialization.Serializable
import org.sales.management.clients.domain.model.Client
import org.sales.management.products.domain.model.Product

@Serializable
data class Sale(
    val id: Long,
    val date: String,
    val client: Client,
    val products: List<Product>,
    val total: Double
)
