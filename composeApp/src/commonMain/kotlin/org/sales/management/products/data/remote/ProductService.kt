package org.sales.management.products.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.HttpStatusCode
import org.sales.management.clients.domain.model.ClientDTO
import org.sales.management.core.data.remote.ktor.ApiConstants
import org.sales.management.products.domain.model.Product
import org.sales.management.products.domain.model.ProductResponse
import org.sales.management.products.domain.model.ProductRequest

class ProductService (
    private val httpClient: HttpClient
){
    private val baseUrl = "${ApiConstants.BASE_URL}/products"

    suspend fun getProducts(): List<Product>? {
        val response : HttpResponse = httpClient.get(baseUrl)

        println(response.status.toString())

        return when (response.status){
            HttpStatusCode.OK -> {
                val productsResponse : ProductResponse = response.body()
                return productsResponse.data
            }
            else -> null
        }
    }

    suspend fun createProduct(request: ProductRequest): Product {
        val response: HttpResponse = httpClient.post(baseUrl) {
            setBody(request)
        }

        return when (response.status) {
            HttpStatusCode.OK -> {
                response.body()
            }

            else -> throw Exception("Erro ao criar produto")
        }
    }

    suspend fun deleteProduct(id: Long) {
        val response: HttpResponse = httpClient.delete("$baseUrl/$id")
    }
}
