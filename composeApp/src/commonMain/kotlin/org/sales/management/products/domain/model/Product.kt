package org.sales.management.products.domain.model

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: Long,
    val name: String,
    val price: String, // Backend implementado com Bigdecimal
    val stock: Int,
)
