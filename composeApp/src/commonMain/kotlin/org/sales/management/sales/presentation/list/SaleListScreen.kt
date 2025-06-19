package org.sales.management.sales.presentation.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import managementsalesapp.composeapp.generated.resources.Res
import managementsalesapp.composeapp.generated.resources.img
import org.jetbrains.compose.resources.painterResource
import org.sales.management.sales.domain.model.sale.SaleResponse
import org.sales.management.sales.domain.model.sale.SaleStatus
import org.sales.management.sales.presentation.list.SaleItemCard
import org.sales.management.sales.presentation.list.SaleListViewModel



@Composable
fun SaleListScreen(
    goToDetail: (saleId: Long) -> Unit,
    goBack: () -> Unit,
    viewModel: SaleListViewModel = koinViewModel()
) {
    val sales by viewModel.salesState.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    var snackColor by remember { mutableStateOf(Color.Unspecified) }

    var showPaymentDialog by remember { mutableStateOf<SaleResponse?>(null) }

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { ev ->
            snackColor = if (ev.isError) Color.Red else Color(0xFF388E3C)
            snackbarHostState.showSnackbar(ev.message)
        }
    }

    Scaffold(
        topBar = { TopBar(title = "Vendas", onBack = { goBack() }) },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) { data ->
            Snackbar(snackbarData = data, containerColor = snackColor)
        }},
    ) { padding ->
        showPaymentDialog?.let { sale ->
            AlertDialog(
                shape = RoundedCornerShape(8.dp),
                onDismissRequest = { showPaymentDialog = null },
                title = { Text(text="Forma de Pagamento:") },
                text = {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        listOf("PIX", "Crédito", "Débito").forEach { type ->
                            Text(
                                text = type,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        viewModel.updateStatus(sale.id, SaleStatus.PAID)
                                        showPaymentDialog = null
                                    }
                                    .padding(8.dp)
                            )
                        }
                    }
                },
                confirmButton = {},
                dismissButton = {
                    TextButton(onClick = { showPaymentDialog = null }) {
                        Text("Cancelar")
                    }
                }
            )
        }

        if (sales.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(Res.drawable.img),
                        contentDescription = null,
                        modifier = Modifier.size(200.dp)
                    )
                    Text(
                        text = "Nenhuma venda feita.",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 20.dp)
                    )
                }
            }
        }
        else{
            LazyColumn(
                contentPadding = padding,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                items(sales) { sale ->
                    SaleItemCard(
                        sale = sale,
                        onClick = { goToDetail(sale.id) },
                        onRequestPayment = { showPaymentDialog = it }
                    )
                }
            }
        }

    }
}
