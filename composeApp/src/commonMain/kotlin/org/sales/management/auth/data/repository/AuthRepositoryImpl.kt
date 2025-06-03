package org.sales.management.auth.data.repository

import org.sales.management.auth.domain.model.CreateUserResponse
import org.sales.management.auth.domain.model.LoginUserResponse
import org.sales.management.auth.domain.repository.AuthRepository


class AuthRepositoryImpl() : AuthRepository  {
    override suspend fun login(email: String, password: String): LoginUserResponse {
        TODO("Not yet implemented")
    }

    override suspend fun signUp(name: String, email: String, password: String): CreateUserResponse {
        TODO("Not yet implemented")
    }
}