package org.sales.management.products.presentation.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import org.sales.management.core.ui.itens.CustomTopBar


@Composable
fun ProductFormsScreen(
    goBack: () -> Unit,
    viewModel: ProductFormsViewModel = koinViewModel()
) {
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf(0) }


    Scaffold(
        topBar = {
            CustomTopBar(
                title = "Cadastro\nde Produto",
                onBack = { goBack() },
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nome") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)
            )

            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Preço") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)
            )

            OutlinedTextField(
                value = stock.toString(),
                onValueChange = { stock = it.toIntOrNull() ?: 0 },
                label = { Text("Estoque") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.saveProduct(name, price, stock)
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Cadastar")
            }
        }
    }
}
