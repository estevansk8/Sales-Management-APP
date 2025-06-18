package org.sales.management.sales.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
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

class SaleService(
    private val httpClient: HttpClient
) {
    private val baseUrl = "${ApiConstants.BASE_URL}/sales"

    suspend fun createSale(saleRequest: SaleRequestDTO): SaleResponse<Sale> {
        val response: HttpResponse = httpClient.post(baseUrl) {
            setBody(saleRequest)
        }

        if (response.status.value !in 200..299) {
            val errorBody: String = response.bodyAsText()
            throw Exception("Erro ${response.status.value}: $errorBody")
        }

        val apiResponse: SaleResponse<Sale> = response.body()

        if (!apiResponse.success || apiResponse.data == null) {
            throw Exception(apiResponse.message)
        }

        return apiResponse
    }

    suspend fun updateSale(sale: SaleRequestDTO) {
        val response = httpClient.put(baseUrl) {
            setBody(sale)
        }

        when (response.status) {
            HttpStatusCode.OK -> {
                val res: SaleResponse<Sale> = response.body()
                if (!res.success) throw Exception(res.message)
            }

            else -> throw Exception("Erro ao atualizar venda: ${response.status}")
        }
    }

}