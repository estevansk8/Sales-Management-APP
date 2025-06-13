package org.sales.management.core.ui

data class SnackbarEvent(
    val message: String,
    val isError: Boolean
)
