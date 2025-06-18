package org.sales.management.sales.domain.model.sale

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import org.sales.management.sales.domain.model.saleitem.SaleItem


@Serializable
data class Sale(
    val id: Long,
    val clientId: Long,
    val clientName: String,
    val saleDate: LocalDate,
    val dueDate: LocalDate?,
    val status: SaleStatus,
    val items: List<SaleItem>,
    val total: String
)
