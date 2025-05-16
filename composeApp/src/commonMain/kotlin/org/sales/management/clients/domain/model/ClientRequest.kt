package org.sales.management.clients.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ClientBody(
    val name: String,
    val phone: String,
    val address: String,
)
