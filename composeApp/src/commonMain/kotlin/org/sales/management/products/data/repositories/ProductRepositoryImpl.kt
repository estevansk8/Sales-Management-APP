package org.sales.management.products.data.repositories

import org.sales.management.products.data.remote.ProductService
import org.sales.management.products.domain.model.Product
import org.sales.management.products.domain.model.ProductRequest
import org.sales.management.products.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val service: ProductService
): ProductRepository {

    override suspend fun getAllProducts(): List<Product>? {
        return service.getProducts()
    }

    override suspend fun getProductById(id: Long): Product? {
        TODO("Not yet implemented")
    }

    override suspend fun getProductsByName(name: String): List<Product>? {
        return service.getProductsByName(name)
    }

    override suspend fun createProduct(request: ProductRequest): Product {
        return service.createProduct(request)
    }

    override suspend fun updateProduct(product: Product): Product {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProduct(id: Long) {
        TODO("Not yet implemented")
    }
}