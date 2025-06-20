package org.sales.management.reports.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class ReportData(
    val title: String,
    val value: String,
    val description: String,
    val icon: ImageVector,
    val backgroundColor: Color
)

