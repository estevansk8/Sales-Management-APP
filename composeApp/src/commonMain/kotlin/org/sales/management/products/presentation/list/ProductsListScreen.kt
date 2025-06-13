package org.sales.management.products.presentation.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import org.sales.management.clients.presentation.list.ClientItem
import org.sales.management.clients.presentation.list.ClientsListViewModel
import org.sales.management.core.ui.itens.CustomTopBar

@Composable
fun ProductsListScreen(
    goToProductForm: () -> Unit,
    goBack: () -> Unit,
    viewModel: ProductsListViewModel = koinViewModel()
) {
    val products = viewModel.products
    val isLoading = viewModel.isLoading

    LaunchedEffect(Unit) {
        viewModel.listProducts()
        println(products)
    }

    Scaffold(
        topBar = {
            CustomTopBar(
                title = "Lista\nde Produtos",
                onBack = { goBack() },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = MaterialTheme.colorScheme.primary,
                onClick = {
                    goToProductForm()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar Cliente",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },
    ){
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    strokeWidth = 2.dp
                )
            }
        } else if(viewModel.products.isEmpty()){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Nenhum produto encontrado!")
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(products) { product ->
                    ProductItem(product)
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        }
    }


}