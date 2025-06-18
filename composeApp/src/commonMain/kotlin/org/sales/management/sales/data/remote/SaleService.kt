package org.sales.management.sales.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import org.sales.management.core.data.remote.ApiConstants
import org.sales.management.sales.domain.model.sale.Sale
import org.sales.management.sales.domain.model.sale.SaleRequestDTO
import org.sales.management.sales.domain.model.sale.SaleResponse
import io.ktor.client.statement.bodyAsText
import org.sales.management.core.data.remote.ApiResponseDTO

class SaleService(
    private val httpClient: HttpClient
) {
    private val baseUrl = "${ApiConstants.BASE_URL}/sales"

    suspend fun createSale(request: SaleRequestDTO) {
        val response = httpClient.post(baseUrl) { setBody(request) }
        when (response.status) {
            HttpStatusCode.Created, HttpStatusCode.OK -> {
                val api = response.body<ApiResponseDTO<SaleResponse>>()
                if (api.data == null || !api.success) {
                    throw Exception(api.message)
                }
            }
            else -> throw Exception("Erro ao registrar venda: ${response.status}")
        }
    }

    suspend fun updateSale(request: SaleRequestDTO) {
        val response = httpClient.put(baseUrl) { setBody(request) }
        when (response.status) {
            HttpStatusCode.OK -> {
                val api = response.body<ApiResponseDTO<SaleResponse>>()
                if (api.data == null || !api.success) {
                    throw Exception(api.message)
                }
            }
            else -> throw Exception("Erro ao atualizar venda: ${response.status}")
        }
    }

    suspend fun getSales(): List<SaleResponse> {
        val response = httpClient.get(baseUrl)
        when (response.status) {
            HttpStatusCode.OK -> {
                val api = response.body<ApiResponseDTO<List<SaleResponse>>>()
                if (!api.success || api.data == null) {
                    throw Exception(api.message)
                }
                return api.data
            }
            else -> throw Exception("Erro ao obter vendas: ${response.status}")
        }
    }
}