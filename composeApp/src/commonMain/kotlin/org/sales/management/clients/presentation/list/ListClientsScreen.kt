package org.sales.management.clients.presentation.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.sales.management.clients.data.remote.ClientsApi

@Composable
fun ListClientsScreen(
    ktorEngine: ClientsApi,
) {

    LaunchedEffect(Unit) {
        val clients = ktorEngine.getClients()
        println(clients)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "List Clients Screen")
        }
    }
}