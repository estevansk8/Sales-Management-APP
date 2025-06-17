package org.sales.management.sales.presentation.form.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import org.sales.management.clients.domain.model.Client

@Composable
fun ClientSelector(
    clients: List<Client>,
    selectedClient: Client?,
    onClientSelected: (Client) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedTextField(
            value = selectedClient?.name ?: "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth(fraction = 0.7f)
                .clickable { expanded = true },
            label = { Text("Cliente") },
            enabled = false,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.People,
                    contentDescription = "Expandir Lista"
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Expandir Lista"
                )
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            clients.forEach { client ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = client.name,
                        )
                    },
                    onClick = {
                        onClientSelected(client)
                        expanded = false
                    }
                )
            }
        }
    }
}
