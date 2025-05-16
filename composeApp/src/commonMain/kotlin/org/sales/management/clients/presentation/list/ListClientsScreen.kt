package org.sales.management.clients.presentation.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import org.sales.management.clients.data.remote.ClientService

@Composable
fun ListClientsScreen(
    viewModel: ListClientsViewModel = koinViewModel()
) {

    LaunchedEffect(Unit) {
        val clients = viewModel.listClients()
        println(clients)
    }

    if (viewModel.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                strokeWidth = 2.dp
            )
        }
    } else if(viewModel.clients.isEmpty()){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "Nenhum cliente encontrado!")
            }
        }
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "List Clients Screen")
            }
        }
    }
}