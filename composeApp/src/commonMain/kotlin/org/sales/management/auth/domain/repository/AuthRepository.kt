package org.sales.management.auth.domain.repository

interface AuthRepository {
    suspend fun login(email: String, password: String): AuthResult<String>
    suspend fun signUp(name: String, email: String, password: String, ): AuthResult<String>
}