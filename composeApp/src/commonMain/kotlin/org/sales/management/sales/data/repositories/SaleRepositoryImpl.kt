package org.sales.management.sales.data.repositories

import kotlinx.coroutines.flow.Flow
import org.sales.management.clients.domain.model.Client
import org.sales.management.products.domain.model.Product
import org.sales.management.sales.data.remote.SaleService
import org.sales.management.sales.domain.model.SaleRequestDTO
import org.sales.management.sales.domain.repository.SaleRepository

class SaleRepositoryImpl(
    private val saleService: SaleService
): SaleRepository {
    override suspend fun searchProducts(name: String): Flow<Result<List<Product>>> {
        TODO("Not yet implemented")
    }

    override suspend fun getClients(): Flow<Result<List<Client>>> {
        TODO("Not yet implemented")
    }

    override suspend fun createSale(saleRequest: SaleRequestDTO): Flow<Result<Unit>> {
        TODO("Not yet implemented")
    }
}