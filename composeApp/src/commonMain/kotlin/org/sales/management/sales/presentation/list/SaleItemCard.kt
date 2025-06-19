package org.sales.management.sales.presentation.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.HourglassEmpty
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.sales.management.sales.domain.model.sale.SaleResponse
import org.sales.management.sales.domain.model.sale.SaleStatus

@Composable
fun SaleItemCard(
    sale: SaleResponse,
    onClick: () -> Unit,
    onChangeStatus: (SaleStatus) -> Unit
) {
    val icon = if (sale.status == SaleStatus.PENDING) Icons.Default.HourglassEmpty else Icons.Default.CheckCircle

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = 4.dp
    ) {
        Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = sale.clientName, fontWeight = FontWeight.Bold)
                Text("Total: R$ ${sale.totalAmount}", style = MaterialTheme.typography.bodyMedium)
                Text("Data: ${sale.saleDate}", style = MaterialTheme.typography.bodySmall)
            }
            Button(onClick = {
                val target = if (sale.status == SaleStatus.PENDING) SaleStatus.PAID else SaleStatus.PENDING
                onChangeStatus(target)
            }) {
                Text(if (sale.status == SaleStatus.PENDING) "Marcar Pago" else "Marcar Pendente")
            }
        }
    }
}
