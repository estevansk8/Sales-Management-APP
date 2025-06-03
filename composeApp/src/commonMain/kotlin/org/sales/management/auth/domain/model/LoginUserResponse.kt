package org.sales.management.auth.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginUserResponse(
    val userName : String,
    val accessToken: String,
    val refreshToken: String
)
