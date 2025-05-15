package org.sales.management.clients.domain.model

data class ClientDTO(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val address: String
)
