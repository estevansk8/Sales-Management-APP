package org.sales.management.sales.domain.model.sale

import kotlinx.serialization.Serializable

@Serializable
data class SaleStatusUpdateRequest(
    val newStatus: SaleStatus
)
