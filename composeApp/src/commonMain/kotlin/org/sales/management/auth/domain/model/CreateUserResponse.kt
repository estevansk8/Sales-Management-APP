package org.sales.management.auth.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class CreateUserResponse(
    val id: Long,
    val name: String,
    val email: String,
    val profilePicture: String?,
    val createdAt: String
)
