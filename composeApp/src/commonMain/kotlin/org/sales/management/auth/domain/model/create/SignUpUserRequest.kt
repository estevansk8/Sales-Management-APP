package org.sales.management.auth.domain.model.create

import kotlinx.serialization.Serializable

@Serializable
data class SignUpUserRequest(
    val name: String,
    val email: String,
    val password: String,
)
