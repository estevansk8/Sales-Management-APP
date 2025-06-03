package org.sales.management.auth.data.repository

import org.sales.management.auth.data.remote.AuthService
import org.sales.management.auth.domain.model.CreateUserResponse
import org.sales.management.auth.domain.model.LoginUserRequest
import org.sales.management.auth.domain.model.LoginUserResponse
import org.sales.management.auth.domain.repository.AuthRepository


class AuthRepositoryImpl(
    private val service: AuthService
) : AuthRepository  {
    override suspend fun login(loginUserRequest: LoginUserRequest): LoginUserResponse {
        return service.login(loginUserRequest)
    }

    override suspend fun signUp(name: String, email: String, password: String): CreateUserResponse {
        TODO("Not yet implemented")
    }
}