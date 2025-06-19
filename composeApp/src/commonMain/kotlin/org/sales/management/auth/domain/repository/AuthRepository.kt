package org.sales.management.auth.domain.repository

import org.sales.management.auth.domain.model.create.SignUpUserRequest
import org.sales.management.auth.domain.model.create.SignUpUserResponse
import org.sales.management.auth.domain.model.login.LoginUserRequest
import org.sales.management.auth.domain.model.login.LoginUserResponse

interface AuthRepository {
    suspend fun login(loginUserRequest: LoginUserRequest): LoginUserResponse
    suspend fun signUp(signUpUserRequest: SignUpUserRequest): SignUpUserResponse
}