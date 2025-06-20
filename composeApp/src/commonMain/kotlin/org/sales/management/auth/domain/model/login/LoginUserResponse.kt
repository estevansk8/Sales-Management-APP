package org.sales.management.auth.domain.model.login

import kotlinx.serialization.Serializable

@Serializable
data class LoginUserResponse(
    val success: Boolean,
    val message: String,
    val data: LoginUserData?
)

@Serializable
data class LoginUserData(
    val username : String,
    val accessToken: String,
    val refreshToken: String
)
