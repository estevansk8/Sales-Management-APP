package org.sales.management.home.domain

data class Feature(
    val title: String,
    val action: () -> Unit
)

