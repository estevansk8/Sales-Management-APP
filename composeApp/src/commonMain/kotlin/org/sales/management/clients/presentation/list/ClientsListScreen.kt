package org.sales.management.clients.presentation.list

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ClientsListScreen(
    goToClientForm: () -> Unit,
    viewModel: ClientsListViewModel = koinViewModel()
) {
    val clients = viewModel.clients
    val isLoading = viewModel.isLoading

    LaunchedEffect(Unit) {
        viewModel.listClients()
        println(clients)
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = MaterialTheme.colorScheme.primary,
                onClick = {
                    goToClientForm()
                }
            ) {

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
        } else if(viewModel.clients.isEmpty()){
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Nenhum cliente encontrado!")
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(clients) { client ->
                    ClientItem(client)
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        }
    }


}