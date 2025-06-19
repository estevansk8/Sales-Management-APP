package org.sales.management.sales.domain.repository

import kotlinx.coroutines.flow.Flow
import org.sales.management.sales.domain.model.sale.SaleRequestDTO
import org.sales.management.sales.domain.model.sale.SaleResponse
import org.sales.management.sales.domain.model.sale.SaleStatus

interface SaleRepository {
    fun createSale(request: SaleRequestDTO): Flow<Result<Unit>>
    fun updateSale(request: SaleRequestDTO): Flow<Result<Unit>>
    fun updateSaleStatus(saleId: Long, newStatus: SaleStatus): Flow<Result<Unit>>
    fun getSales(): Flow<Result<List<SaleResponse>>>
}