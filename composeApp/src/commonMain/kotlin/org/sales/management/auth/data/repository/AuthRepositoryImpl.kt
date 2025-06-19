package org.sales.management.auth.data.repository

import org.sales.management.auth.data.remote.AuthService
import org.sales.management.auth.domain.model.create.SignUpUserRequest
import org.sales.management.auth.domain.model.create.SignUpUserResponse
import org.sales.management.auth.domain.model.login.LoginUserRequest
import org.sales.management.auth.domain.model.login.LoginUserResponse
import org.sales.management.auth.domain.repository.AuthRepository


class AuthRepositoryImpl(
    private val service: AuthService
) : AuthRepository  {
    override suspend fun login(loginUserRequest: LoginUserRequest): LoginUserResponse {
        return service.login(loginUserRequest)
    }

    override suspend fun signUp(signUpUserRequest: SignUpUserRequest): SignUpUserResponse {
        return service.signUp(signUpUserRequest)
    }
}