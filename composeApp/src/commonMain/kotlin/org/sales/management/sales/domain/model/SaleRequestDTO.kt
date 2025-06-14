package org.sales.management.sales.domain.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import org.sales.management.products.domain.model.ProductRequest

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
