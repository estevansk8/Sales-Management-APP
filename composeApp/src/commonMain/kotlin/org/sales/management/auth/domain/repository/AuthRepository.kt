package org.sales.management.auth.domain.repository

import org.sales.management.auth.domain.model.CreateUserResponse
import org.sales.management.auth.domain.model.LoginUserResponse

interface AuthRepository {
    suspend fun login(email: String, password: String): LoginUserResponse?
    suspend fun signUp(name: String, email: String, password: String, ): CreateUserResponse?
}