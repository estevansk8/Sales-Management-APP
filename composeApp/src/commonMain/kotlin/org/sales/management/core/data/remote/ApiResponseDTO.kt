package org.sales.management.core.data.remote

data class ApiResponseDTO<T>(
    val success: Boolean,
    val message: String,
    val data: T? = null,
)