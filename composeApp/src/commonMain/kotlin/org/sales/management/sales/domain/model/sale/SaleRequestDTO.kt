package org.sales.management.sales.domain.model.sale

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import org.sales.management.sales.domain.model.saleitem.SaleItemRequestDTO

@Serializable
data class SaleRequestDTO(
    val clientId: Long,

    // kotlinx.serialization serializa LocalDate para o formato "YYYY-MM-DD" por padrão,
    // que o Spring Boot entende.
    val saleDate: LocalDate,

    val status: SaleStatus,
    val dueDate: LocalDate?,
    val items: List<SaleItemRequestDTO>
)
