package org.sales.management.auth.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import org.sales.management.auth.domain.model.CreateUserRequest
import org.sales.management.auth.domain.model.CreateUserResponse
import org.sales.management.auth.domain.model.LoginUserRequest
import org.sales.management.auth.domain.model.LoginUserResponse
import org.sales.management.clients.domain.model.ClientDTO
import org.sales.management.core.ktor.ApiConstants

class AuthService (
    val httpClient: HttpClient
){
    private val baseUrl = "${ApiConstants.BASE_URL}/auth"

    suspend fun login(request: LoginUserRequest): LoginUserResponse? {
        val response: HttpResponse = httpClient.post("$baseUrl/login") {
            setBody(request)
        }

        return when (response.status) {
            HttpStatusCode.OK -> {
                response.body()
            }
            else -> throw Exception("Erro ao logar")
        }
    }

    suspend fun signUp(request: CreateUserRequest): CreateUserResponse? {
        val response: HttpResponse = httpClient.post("$baseUrl/create") {
            setBody(request)
        }

        return when (response.status) {
            HttpStatusCode.OK -> {
                response.body()
            }

            else -> throw Exception("Erro ao criar usuário")
        }
    }

}