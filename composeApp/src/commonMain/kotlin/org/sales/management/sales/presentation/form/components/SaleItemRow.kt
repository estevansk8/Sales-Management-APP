package org.sales.management.sales.presentation.form.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.sales.management.sales.domain.model.saleitem.SaleItem
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.text.font.FontWeight



@Composable
fun SaleItemRow(
    item: SaleItem,
    onQuantityChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // 1. Controle de Quantidade
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { onQuantityChange(-1) }) {
                Icon(Icons.Default.Remove, "Diminuir")
            }
            Text(item.quantity.toString(), style = MaterialTheme.typography.h5)
            IconButton(onClick = { onQuantityChange(1) }) {
                Icon(Icons.Default.Add, "Aumentar")
            }
        }

        // 2. Nome e Preço Unitário
        Column(modifier = Modifier.weight(1f).padding(horizontal = 8.dp)) {
            Text(item.name, fontWeight = FontWeight.Bold)
            Text("R$ ${item.unitPrice.toPlainString()}", style = MaterialTheme.typography.h5)
        }

        // 3. Preço Total do Item
        Text("R$ ${item.subtotal.toPlainString()}", fontWeight = FontWeight.SemiBold)
    }
}