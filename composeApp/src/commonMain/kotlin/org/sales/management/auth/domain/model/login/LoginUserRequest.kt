package org.sales.management.auth.domain.model.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginUserRequest(
    val email: String,
    val password: String,
)
