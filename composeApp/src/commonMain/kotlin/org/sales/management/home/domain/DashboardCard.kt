package org.sales.management.home.domain

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class DashboardCard(
    val title: String,
    val subtitle: String,
    val amount: String,
    val amountColor: Color,
    val backgroundColor: Color,
    val icon: ImageVector
)

