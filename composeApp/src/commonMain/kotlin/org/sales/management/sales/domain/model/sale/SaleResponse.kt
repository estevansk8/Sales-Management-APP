package org.sales.management.sales.domain.model.sale

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable
import org.sales.management.sales.domain.model.saleitem.SaleItemResponseDTO

@Serializable
data class SaleResponse(
    val id: Long,
    val clientId: Long,
    val clientName: String,
    val saleDate: LocalDate,
    val totalAmount: String,
    val status: SaleStatus,
    val dueDate: LocalDate?,
    val createdAt: LocalDateTime,
    val items: List<SaleItemResponseDTO>
)
