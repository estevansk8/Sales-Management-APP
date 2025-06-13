package org.sales.management.products.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.sales.management.products.domain.model.Product

@Composable
fun ProductItem(product: Product) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Edit,
            contentDescription = "Editar",
            tint = Color.Black,
            modifier = Modifier
                .size(24.dp)
                .weight(0.5f)
        )
        Text(
            text = product.name,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .weight(3f)
                .padding(start = 8.dp),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "R$ ${product.price}",
            modifier = Modifier.weight(2.5f),
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = product.stock.toString(),
            modifier = Modifier.weight(0.5f),
            style = MaterialTheme.typography.bodyLarge
        )
        if (product.stock in 1..5) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = "Editar",
                tint = Color.Gray,
                modifier = Modifier
                    .size(24.dp)
            )
        }
        if (product.stock > 5) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "Editar",
                tint = Color(0xFF66A06F),
                modifier = Modifier
                    .size(24.dp)
            )
        }
        if (product.stock <= 0) {
            Icon(
                imageVector = Icons.Default.Error,
                contentDescription = "Editar",
                tint = Color.Red,
                modifier = Modifier
                    .size(24.dp)
            )
        }
    }
}
