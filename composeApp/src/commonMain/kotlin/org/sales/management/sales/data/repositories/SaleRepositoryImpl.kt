package org.sales.management.sales.data.repositories

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.sales.management.sales.data.remote.SaleService
import org.sales.management.sales.domain.model.sale.SaleRequestDTO
import org.sales.management.sales.domain.model.sale.SaleResponse
import org.sales.management.sales.domain.model.sale.SaleStatus
import org.sales.management.sales.domain.repository.SaleRepository

class SaleRepositoryImpl(
    private val saleService: SaleService
): SaleRepository {

    override fun createSale(request: SaleRequestDTO): Flow<Result<Unit>> = flow {
        try {
            saleService.createSale(request)
            emit(Result.success(Unit))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override fun updateSale(request: SaleRequestDTO): Flow<Result<Unit>> = flow {
        try {
            saleService.updateSale(request)
            emit(Result.success(Unit))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override fun updateSaleStatus(saleId: Long, newStatus: SaleStatus): Flow<Result<Unit>> = flow {
        try {
            saleService.updateSaleStatus(saleId, newStatus)
            emit(Result.success(Unit))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }

    override fun getSales(): Flow<Result<List<SaleResponse>>> = flow {
        try {
            val list = saleService.getSales()
            emit(Result.success(list))
        } catch (e: Exception) {
            emit(Result.failure(e))
        }
    }
}