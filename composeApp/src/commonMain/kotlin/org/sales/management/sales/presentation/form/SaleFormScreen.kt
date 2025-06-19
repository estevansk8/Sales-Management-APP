package org.sales.management.sales.presentation.form

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import org.sales.management.core.ui.composables.TopBar
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import managementsalesapp.composeapp.generated.resources.Res
import managementsalesapp.composeapp.generated.resources.img
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.sales.management.sales.presentation.form.components.ClientSelector
import org.sales.management.sales.presentation.form.components.DueDatePicker
import org.sales.management.sales.presentation.form.components.ProductSearchField
import org.sales.management.sales.presentation.form.components.SaleItemRow
import org.sales.management.sales.presentation.form.components.TotalFooter

@Composable
fun SaleFormScreen(
    goBack: () -> Unit,
    viewModel: SaleFormsViewModel = koinViewModel()
){

    val uiState by viewModel.uiState.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    var snackbarColor by remember { mutableStateOf(Color.Unspecified) }

    LaunchedEffect(Unit) {
        viewModel.eventFlow.collect { event ->
            snackbarColor = if (event.isError) Color(0xFFD32F2F) else Color(0xFF388E3C)
            snackbarHostState.showSnackbar(event.message)
        }
    }

    Scaffold(
        topBar = {
            TopBar(
                title = "Realizar Venda",
                onBack = goBack
            )
        },
        bottomBar = {
            TotalFooter(
                totalAmount = uiState.totalAmount,
                onSubmit = viewModel::onSubmitSale,
                isSubmitting = false
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = snackbarColor
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .background(Color.White),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {


            item {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ){
                    ClientSelector(
                        clients = uiState.clientList,
                        selectedClient = uiState.selectedClient,
                        onClientSelected = viewModel::onClientSelected
                    )

                    DueDatePicker(
                        selectedDate = uiState.dueDate,
                        onDateSelected = viewModel::onDueDateSelected,
                        modifier = Modifier.weight(1f)
                    )

                }

            }

            item {
                ProductSearchField(
                    query = uiState.searchQuery,
                    onQueryChange = viewModel::onSearchQueryChange,
                    searchResults = uiState.searchResults,
                    onProductSelected = viewModel::onProductSelected,
                    isSearching = uiState.isSearching
                )
            }




            if (uiState.saleItems.isNotEmpty()) {
                items(uiState.saleItems) { cartItem ->
                    SaleItemRow(
                        item = cartItem,
                        onQuantityChange = { change ->
                            viewModel.onQuantityChange(cartItem.productId, change)
                        }
                    )
                }
            }else {
                item {
                    Box(
                        modifier = Modifier.fillMaxSize().padding(paddingValues),
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
                                text = "Nenhum item\nadicionado por enquanto.",
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(top = 20.dp)
                            )
                        }
                    }
                }
            }

        }
    }

}