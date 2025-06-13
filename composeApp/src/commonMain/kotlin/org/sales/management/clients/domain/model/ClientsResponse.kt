package org.sales.management.clients.domain.model
import kotlinx.serialization.Serializable

@Serializable
data class ClientsResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T,
)