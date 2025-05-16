package org.sales.management.clients.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import org.sales.management.clients.domain.model.Client
import org.sales.management.clients.domain.model.ClientRequest
import org.sales.management.clients.domain.model.ClientDTO
import org.sales.management.clients.domain.model.ClientsResponse

class ClientService (
    private val httpClient: HttpClient
){
    private val baseUrl = "http://10.10.0.175:8080/clients"

    suspend fun getClients(): List<Client>? {
        val response : HttpResponse = httpClient.get(baseUrl)

        println(response.status.toString())

        return when (response.status){
            HttpStatusCode.OK -> {
                val clientsResponse : ClientsResponse = response.body()
                return clientsResponse.data
            }
            else -> null
        }
    }

    suspend fun createClient(request: ClientRequest): ClientDTO {
        val response: HttpResponse = httpClient.post(baseUrl) {
            setBody(request)
        }

        return when (response.status) {
            HttpStatusCode.OK -> {
                response.body()
            }

            else -> throw Exception("Erro ao criar cliente")
        }
    }

    suspend fun deleteClient(id: Long) {
        val response: HttpResponse = httpClient.delete("$baseUrl/$id")
    }
}