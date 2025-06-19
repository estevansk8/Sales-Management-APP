package org.sales.management.auth.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import org.sales.management.auth.domain.model.create.SignUpUserRequest
import org.sales.management.auth.domain.model.create.SignUpUserResponse
import org.sales.management.auth.domain.model.login.LoginUserRequest
import org.sales.management.auth.domain.model.login.LoginUserResponse
import org.sales.management.core.data.remote.ApiConstants
import org.sales.management.core.data.remote.ApiResponseDTO

class AuthService (
    val httpClient: HttpClient
){
    private val baseUrl = "${ApiConstants.BASE_URL}/auth"

    suspend fun login(request: LoginUserRequest): LoginUserResponse {
        try {
            val response= httpClient.post("$baseUrl/login") { setBody(request) }

            val loginResponse = response.body<LoginUserResponse>()

            // Caso estranho: Status de erro, mas API diz sucesso.
            if (response.status != HttpStatusCode.OK && loginResponse.success) {
                return LoginUserResponse(success = false, message = "Resposta inconsistente da API.", data = null)
            }
            return loginResponse
        } catch (e: Exception){
            println("AuthService - Erro na chamada de login: ${e.message}")
            return LoginUserResponse(
                success = false,
                message = "Erro de comunicação com o servidor: ${e.message ?: "Erro desconhecido"}",
                data = null
            )
        }
    }

    suspend fun signUp(request: SignUpUserRequest): SignUpUserResponse {
        val response = httpClient.post("$baseUrl/user") { setBody(request) }

        return when (response.status) {
            HttpStatusCode.Created -> {
                val signUpResponse = response.body<ApiResponseDTO<SignUpUserResponse>>()
                if (!signUpResponse.success || signUpResponse.data == null) {
                    throw Exception(signUpResponse.message)
                }
                signUpResponse.data
            }
            else -> throw Exception("Erro ao criar usuário: ${response.status}")
        }
    }

}