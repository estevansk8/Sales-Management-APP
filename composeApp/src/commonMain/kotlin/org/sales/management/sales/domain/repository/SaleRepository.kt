package org.sales.management.sales.domain.repository

import org.sales.management.clients.domain.model.Client
import org.sales.management.products.domain.model.Product

interface SaleRepository {
    suspend fun searchProducts(name: String): List<Product>
    suspend fun getClients(): List<Client>
    suspend fun createSale(saleRequest: SaleRequestDTO): Result<Unit>
}