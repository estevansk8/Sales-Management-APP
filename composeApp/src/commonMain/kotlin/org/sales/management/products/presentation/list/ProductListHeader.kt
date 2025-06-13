package org.sales.management.products.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ProductListHeader(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF66A06F))
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Spacer(modifier = Modifier.weight(0.5f))
        Text(
            text = "Nome",
            color = Color.White,
            modifier = Modifier.weight(3.5f).padding(start = 8.dp),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Preço",
            color = Color.White,
            modifier = Modifier.weight(2f),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Estoque",
            color = Color.White,
            modifier = Modifier.weight(1f),
            fontWeight = FontWeight.Bold
        )
    }
}