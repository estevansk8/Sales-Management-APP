package org.sales.management.sales.domain.repository

import kotlinx.coroutines.flow.Flow
import org.sales.management.sales.domain.model.sale.SaleRequestDTO

interface SaleRepository {
    fun createSale(request: SaleRequestDTO): Flow<Result<Unit>>
    fun updateSale(request: SaleRequestDTO): Flow<Result<Unit>>
}