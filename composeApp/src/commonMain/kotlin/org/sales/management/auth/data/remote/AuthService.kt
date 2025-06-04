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
import org.sales.management.core.data.remote.ktor.ApiConstants

class AuthService (
    val httpClient: HttpClient
){
    private val baseUrl = "${ApiConstants.BASE_URL}/auth"

    suspend fun login(request: LoginUserRequest): LoginUserResponse {
        try {
            val response: HttpResponse = httpClient.post("$baseUrl/login") {
                setBody(request)
            }
            print("Response status: ${response.status}")
            val loginResponse = response.body<LoginUserResponse>()

            // Caso estranho: Status de erro, mas API diz sucesso.
            // Poderia lançar uma exceção específica ou retornar uma resposta de erro padronizada.
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