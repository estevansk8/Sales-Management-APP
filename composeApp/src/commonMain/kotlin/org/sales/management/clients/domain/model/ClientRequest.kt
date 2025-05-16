package org.sales.management.clients.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ClientRequest(
    val name: String,
    val phone: String,
    val address: String,
)
