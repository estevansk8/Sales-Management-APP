package org.sales.management.products.domain.model
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse(
    val success: Boolean,
    val message: String,
    val data: List<Product>,
)

