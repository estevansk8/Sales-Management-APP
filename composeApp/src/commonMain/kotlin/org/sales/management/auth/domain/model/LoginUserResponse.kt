package org.sales.management.auth.domain.model

data class LoginUserResponse(
    val userName : String,
    val accessToken: String,
    val refreshToken: String
)
