package org.sales.management.clients.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import org.sales.management.clients.domain.model.Client
import org.sales.management.clients.domain.model.ClientsResponse

class ClientService (
    private val httpClient: HttpClient
){
    private val baseUrl = "http://192.168.1.169:8080/clients"

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
}