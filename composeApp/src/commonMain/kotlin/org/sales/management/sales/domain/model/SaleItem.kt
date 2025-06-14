package org.sales.management.sales.domain.model

import com.ionspin.kotlin.bignum.decimal.BigDecimal

data class SaleItem(
    val productId: Long,
    val name: String,
    val unitPrice: BigDecimal,
    val quantity: Int = 1
) {
    val subtotal: BigDecimal
        get() = unitPrice * BigDecimal.fromInt(quantity)
}

