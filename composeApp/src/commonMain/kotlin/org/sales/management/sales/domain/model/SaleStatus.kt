package org.sales.management.sales.domain.model

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