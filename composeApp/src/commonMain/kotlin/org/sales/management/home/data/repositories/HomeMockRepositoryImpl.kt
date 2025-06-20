package org.sales.management.home.data.repositories

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.EvStation
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.ui.graphics.Color
import org.sales.management.home.domain.DashboardCard

class HomeMockRepositoryImpl {
    val mockDashboardCards = listOf(
        DashboardCard(
            title = "Pix",
            subtitle = "Dona Maria",
            amount = "R$200,00",
            amountColor = Color(0xFF1B5E20),
            backgroundColor = Color(0xFFC8E6C9),
            icon = Icons.Default.SwapVert
        ),
        DashboardCard(
            title = "Gasolina",
            subtitle = "Gasto realizado",
            amount = "-R$120,00",
            amountColor = Color(0xFFD32F2F),
            backgroundColor = Color(0xFFE0E0E0),
            icon = Icons.Default.EvStation
        ),
        DashboardCard(
            title = "Marcos do Bar",
            subtitle = "Cliente adicionado",
            amount = "",
            amountColor = Color.Black,
            backgroundColor = Color(0xFFE0E0E0),
            icon = Icons.Default.Person
        ),
        DashboardCard(
            title = "Venda Realizada",
            subtitle = "Venda PIX",
            amount = "R$137,50",
            amountColor = Color(0xFF1B5E20),
            backgroundColor = Color(0xFFC8E6C9),
            icon = Icons.Default.CheckCircle
        ),
        DashboardCard(
            title = "Crédito",
            subtitle = "Máquina",
            amount = "R$89,90",
            amountColor = Color(0xFF1B5E20),
            backgroundColor = Color(0xFFC8E6C9),
            icon = Icons.Default.CreditCard
        )
    )

}