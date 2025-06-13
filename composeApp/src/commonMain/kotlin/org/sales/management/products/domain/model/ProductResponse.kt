package org.sales.management.products.domain.model
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import kotlinx.serialization.Serializable

@Serializable
data class ProductResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T,
)

