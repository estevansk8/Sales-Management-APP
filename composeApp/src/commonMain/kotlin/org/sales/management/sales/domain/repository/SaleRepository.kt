package org.sales.management.sales.domain.repository

import kotlinx.coroutines.flow.Flow
import org.sales.management.clients.domain.model.Client
import org.sales.management.products.domain.model.Product
import org.sales.management.sales.domain.model.SaleRequestDTO

interface SaleRepository {
    suspend fun searchProducts(name: String): Flow<Result<List<Product>>>
    suspend fun getClients(): Flow<Result<List<Client>>>
    suspend fun createSale(saleRequest: SaleRequestDTO): Flow<Result<Unit>>
}