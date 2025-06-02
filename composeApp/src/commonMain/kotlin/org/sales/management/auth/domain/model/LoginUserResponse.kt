package org.sales.management.auth.domain.model

data class LoginUserResponse(
    val accessToken: String,
    val refreshToken: String
)
