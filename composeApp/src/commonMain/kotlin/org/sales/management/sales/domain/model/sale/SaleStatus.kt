package org.sales.management.sales.domain.model.sale

import kotlinx.serialization.Serializable

@Serializable
enum class SaleStatus {
    PENDING,
    PAID,
    PROCESSING,
    SHIPPED,
    DELIVERED,
    CANCELLED,
    REFUNDED
}