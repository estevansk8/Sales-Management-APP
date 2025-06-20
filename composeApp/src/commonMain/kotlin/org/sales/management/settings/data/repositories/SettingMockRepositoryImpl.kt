package org.sales.management.settings.data.repositories

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CloudSync
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.SystemUpdate
import androidx.compose.material.icons.filled.Warning
import org.sales.management.settings.domain.model.SettingItem

class SettingMockRepositoryImpl {
    val mockSettings = listOf(
        SettingItem(
            title = "Alerta de Estoque Baixo",
            value = "Quando estoque < 5 unidades",
            icon = Icons.Default.Warning
        ),
        SettingItem(
            title = "Alerta de Cobrança",
            value = "3 dias antes do vencimento",
            icon = Icons.Default.Notifications
        ),
        SettingItem(
            title = "Tema do Aplicativo",
            value = "Claro",
            icon = Icons.Default.DarkMode
        ),
        SettingItem(
            title = "Atualizações automáticas",
            value = "Ativado",
            icon = Icons.Default.SystemUpdate
        ),
        SettingItem(
            title = "Sincronizar com nuvem",
            value = "A cada 24 horas",
            icon = Icons.Default.CloudSync
        )
    )

}