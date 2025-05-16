package org.sales.management.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.sales.management.home.presentation.itens.FeatureCard
import org.sales.management.home.presentation.itens.SalesCard
import androidx.compose.material3.Text
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.sales.management.home.domain.Feature


@Composable
fun HomeScreen(
    goToClients : () -> Unit,
){
    val listofFeatures = listOf(
        Feature("Clientes", goToClients),
        Feature("Produtos", {  }),
        Feature("Vendas", {  }),
        Feature("Relatórios", {  }),
    )

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Conteúdo principal
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            SalesCard {  }

            Text(
                text = "Meu negócio",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start).padding(start = 16.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
            ) {

                listofFeatures.forEach { feature ->
                    FeatureCard(
                        feature = feature.title,
                        goToFeature = feature.action
                    )
                }
            }
        }
        // Topo
    }
}