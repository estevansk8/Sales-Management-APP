package org.sales.management.auth.domain.model

data class CreateUserRequest(
    val name: String,
    val email: String,
    val password: String,
)
