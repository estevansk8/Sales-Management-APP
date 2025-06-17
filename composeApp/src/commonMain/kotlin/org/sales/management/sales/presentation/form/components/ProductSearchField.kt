package org.sales.management.sales.presentation.form.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.sales.management.products.domain.model.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductSearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    searchResults: List<Product>,
    onProductSelected: (Product) -> Unit,
    isSearching: Boolean
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded && searchResults.isNotEmpty(),
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier.fillMaxWidth().menuAnchor(),
            label = { Text("Buscar Produto") },
            trailingIcon = {
                if (isSearching) CircularProgressIndicator(modifier = Modifier.size(24.dp)) else Icon(
                    Icons.Default.Search, contentDescription = "Buscar")
            }
        )
        ExposedDropdownMenu(
            expanded = expanded && searchResults.isNotEmpty(),
            onDismissRequest = { expanded = false }
        ) {
            searchResults.forEach { product ->
                DropdownMenuItem(
                    text = { Text(product.name) },
                    onClick = {
                        onProductSelected(product)
                        expanded = false
                    }
                )
            }
        }
    }
}
