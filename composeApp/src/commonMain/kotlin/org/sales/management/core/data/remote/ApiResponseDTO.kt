package org.sales.management.core.data.remote

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponseDTO<T>(
    val success: Boolean,
    val message: String,
    val data: T? = null,
)