package org.sales.management.sales.presentation.list

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.ui.graphics.Color
import androidx.compose.material.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import org.sales.management.core.ui.composables.TopBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import org.sales.management.sales.presentation.list.SaleItemCard
import org.sales.management.sales.presentation.list.SaleListViewModel



@Composable
fun SaleListScreen(
    goToDetail: (saleId: Long) -> Unit,
    viewModel: SaleListViewModel = koinViewModel()
) {
    val sales by viewModel.salesState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    var snackColor by remember { mutableStateOf(Color.Unspecified) }

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { ev ->
            snackColor = if (ev.isError) Color.Red else Color.Green
            snackbarHostState.showSnackbar(ev.message)
        }
    }

    Scaffold(
        topBar = { TopBar(title = "Vendas", onBack = {}) },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) { data ->
            Snackbar(snackbarData = data, containerColor = snackColor)
        }},
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(sales) { sale ->
                SaleItemCard(
                    sale = sale,
                    onClick = { goToDetail(sale.id) },
                    onChangeStatus = { newStatus ->
                        viewModel.updateStatus(sale, newStatus)
                    }
                )
            }
        }
    }
}
