package org.sales.management.sales.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class SaleResponse(
    val success: Boolean,
    val message: String,
    val data: List<Sale>,
)
