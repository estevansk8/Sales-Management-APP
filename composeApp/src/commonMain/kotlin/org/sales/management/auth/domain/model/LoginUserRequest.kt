package org.sales.management.auth.domain.model

data class LoginUserRequest(
    val email: String,
    val password: String,
)
