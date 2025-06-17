package org.sales.management.sales.presentation.form

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import org.sales.management.core.ui.composables.TopBar
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import org.koin.compose.viewmodel.koinViewModel
import org.sales.management.sales.domain.model.SaleItem
import org.sales.management.sales.presentation.form.components.ProductSearchField
import org.sales.management.sales.presentation.form.components.SaleItemRow
import org.sales.management.sales.presentation.form.components.TotalFooter

@Composable
fun SaleFormScreen(
    viewModel: SaleFormsViewModel = koinViewModel()
){

//    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { TopBar("Registrar Venda"){} },
        bottomBar = {
            TotalFooter(
                totalAmount = BigDecimal.ZERO,
                onSubmit = {  },
                isSubmitting = false
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                ProductSearchField(
                    query = uiState.searchQuery,
                    onQueryChange = {},
                    searchResults = uiState.searchResults,
                    onProductSelected = {  },
                    isSearching = uiState.isSearching
                )
            }

            item {
//                ClientSelector(
//                    clients = uiState.clientList,
//                    selectedClient = uiState.selectedClient,
//                    onClientSelected = viewModel::onClientSelected
//                )
            }

            item { /* Componente de DatePicker para a Data de Vencimento */ }



            items(uiState.cartItems) { cartItem ->
                SaleItemRow(
                    item = cartItem,
                    onQuantityChange = {
                        //TODO: change -> viewModel.onQuantityChange(cartItem.productId, change)
                    }
                )
            }
        }
    }

}