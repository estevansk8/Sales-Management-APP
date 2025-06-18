package org.sales.management.sales.domain.model.saleitem

import com.ionspin.kotlin.bignum.decimal.BigDecimal
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class SaleItem(
    val productId: Long,
    val name: String,
    @Contextual val unitPrice: BigDecimal,
    val quantity: Int = 1
) {
    val subtotal: BigDecimal
        get() = unitPrice * BigDecimal.fromInt(quantity)
}

