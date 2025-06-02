package org.sales.management.auth.domain.model

data class CreateUserResponse(
    val id: Long,
    val name: String,
    val email: String,
    val profilePicture: String?,
    val createdAt: String
)
