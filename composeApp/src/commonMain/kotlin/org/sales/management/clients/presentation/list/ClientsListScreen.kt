package org.sales.management.clients.presentation.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import managementsalesapp.composeapp.generated.resources.Res
import managementsalesapp.composeapp.generated.resources.img
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.sales.management.core.ui.composables.TopBar

@Composable
fun ClientsListScreen(
    goToClientForm: () -> Unit,
    goBack: () -> Unit,
    viewModel: ClientsListViewModel = koinViewModel()
) {
    val clients = viewModel.clients
    val isLoading = viewModel.isLoading

    LaunchedEffect(Unit) {
        viewModel.listClients()
        println(clients)
    }

    Scaffold(
        topBar = {
            TopBar(
                title = "Lista\nde Clientes",
                onBack = { goBack() },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                backgroundColor = MaterialTheme.colorScheme.primary,
                onClick = {
                    goToClientForm()
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
        } else if(viewModel.clients.isEmpty()){
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
                        text = "Nenhum cliente adicionado.",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 20.dp)
                    )
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