package org.sales.management.home.domain

import androidx.compose.ui.graphics.vector.ImageVector

data class Feature(
    val image: ImageVector,
    val title: String,
    val action: () -> Unit
)

