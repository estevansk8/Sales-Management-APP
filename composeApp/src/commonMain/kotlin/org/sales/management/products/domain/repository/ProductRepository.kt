package org.sales.management.products.domain.repository

import org.sales.management.products.domain.model.Product
import org.sales.management.products.domain.model.ProductRequest

interface ProductRepository {
    suspend fun getAllProducts(): List<Product>?
    suspend fun getProductById(id: Long): Product?
    suspend fun getProductsByName(name: String): List<Product>?
    suspend fun createProduct(request: ProductRequest): Product
    suspend fun updateProduct(product: Product): Product
    suspend fun deleteProduct(id: Long)
}