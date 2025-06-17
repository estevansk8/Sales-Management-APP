package org.sales.management.sales.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import org.sales.management.core.data.remote.ktor.ApiConstants
import org.sales.management.sales.domain.model.Sale
import org.sales.management.sales.domain.model.SaleRequestDTO
import org.sales.management.sales.domain.model.SaleResponse

class SaleService(
    private val httpClient: HttpClient
) {
    private val baseUrl = "${ApiConstants.BASE_URL}/sales"

    suspend fun getSales(): List<Sale> {
        val response: HttpResponse = httpClient.get(baseUrl)

        return when (response.status) {
            HttpStatusCode.OK -> {
                val salesResponse: SaleResponse = response.body()
                salesResponse.data
            }

            else -> throw Exception("Erro ao buscar vendas")
        }
    }

    suspend fun createSale(saleRequest: SaleRequestDTO): SaleResponse {
        val response: HttpResponse = httpClient.post(baseUrl) {
            setBody(saleRequest)
        }

        return when (response.status) {
            HttpStatusCode.Created, HttpStatusCode.OK -> {
                val saleResponse: SaleResponse = response.body()
                saleResponse
            }

            else -> throw Exception("Erro ao criar venda")
        }
    }

}