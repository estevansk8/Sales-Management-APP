package org.sales.management.auth.domain.model.create

import kotlinx.serialization.Serializable

@Serializable
data class SignUpUserResponse(
    val id: Long,
    val name: String,
    val email: String,
    val profilePicture: String?,
    val createdAt: String
)
