package org.sales.management.reports.data.repositories

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.ui.graphics.Color
import org.sales.management.reports.domain.model.ReportData

class ReportsRepositoryMockImpl {
    val mockReportData = listOf(
        ReportData(
            title = "Vendas por PIX",
            value = "R$ 1.250,00",
            description = "24 transações",
            icon = Icons.Default.QrCode,
            backgroundColor = Color(0xFFB2DFDB)
        ),
        ReportData(
            title = "Vendas por Débito",
            value = "R$ 980,00",
            description = "18 transações",
            icon = Icons.Default.Payment,
            backgroundColor = Color(0xFFB2DFDB)
        ),
        ReportData(
            title = "Vendas por Crédito",
            value = "R$ 2.310,00",
            description = "32 transações",
            icon = Icons.Default.CreditCard,
            backgroundColor = Color(0xFFB2DFDB)
        ),
        ReportData(
            title = "Total de Vendas",
            value = "R$ 4.540,00",
            description = "74 no total",
            icon = Icons.Default.AttachMoney,
            backgroundColor = Color(0xFFD1C4E9)
        ),
        ReportData(
            title = "Clientes Ativos",
            value = "4",
            description = "Clientes que compraram este mês",
            icon = Icons.Default.Group,
            backgroundColor = Color(0xFFFFF9C4)
        )
    )

}