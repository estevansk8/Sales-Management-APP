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
import org.sales.management.core.data.remote.ktor.ApiConstants

class ClientService (
    private val httpClient: HttpClient
){
    private val baseUrl = "${ApiConstants.BASE_URL}/clients"

    suspend fun getClients(): List<Client>? {
        val response : HttpResponse = httpClient.get(baseUrl)

        println(response.status.toString())

        val clientsResponse: ClientsResponse<List<Client>> = response.body()


        return when (response.status){
            HttpStatusCode.OK -> {
                return clientsResponse.data
            }
            else -> null
        }
    }

    suspend fun createClient(request: ClientRequest): ClientDTO {
        val response: HttpResponse = httpClient.post(baseUrl) {
            setBody(request)
        }

        val clientResponse: ClientsResponse<ClientDTO> = response.body()
        val client = clientResponse.data

        return when (response.status) {
            HttpStatusCode.OK,
            HttpStatusCode.Created->  client
            else -> throw Exception("${response.status}")
        }
    }

    suspend fun deleteClient(id: Long) {
        val response: HttpResponse = httpClient.delete("$baseUrl/$id")
    }
}