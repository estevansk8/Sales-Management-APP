package org.sales.management.clients.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Client(
    val id: Int,
    val name: String,
    val phone: String,
    val address: String,
)