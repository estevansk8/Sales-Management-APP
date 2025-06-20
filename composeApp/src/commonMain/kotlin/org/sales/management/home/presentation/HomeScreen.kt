package org.sales.management.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.sales.management.home.presentation.itens.FeatureCard
import org.sales.management.home.presentation.itens.SalesCard
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import managementsalesapp.composeapp.generated.resources.Res
import managementsalesapp.composeapp.generated.resources.img
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.sales.management.core.ui.composables.BottomBar
import org.sales.management.home.data.repositories.HomeMockRepositoryImpl
import org.sales.management.home.domain.Feature
import org.sales.management.products.presentation.list.ProductItem
import org.sales.management.products.presentation.list.ProductListHeader
import org.sales.management.home.presentation.itens.DashboardCardItem


@Composable
fun HomeScreen(
    goMakeSale : () -> Unit,
    goToClients : () -> Unit,
    goToProducts : () -> Unit,
    goToSales : () -> Unit,
    onExit: () -> Unit,
    viewModel: HomeViewModel = koinViewModel()
){
    val userName = viewModel.userName
    var showMenu by remember { mutableStateOf(false) }
    var selectedTab by remember { mutableStateOf(0) }

    val listofFeatures = listOf(
        Feature(Icons.Default.People,"Clientes", goToClients),
        Feature(Icons.Default.Inventory2,"Produtos", goToProducts),
        Feature(Icons.Default.ShoppingCart,"Vendas", goToSales),
        Feature(Icons.Default.Money,"Gastos", {  }),
    )

    val mockCards = HomeMockRepositoryImpl().mockDashboardCards

    Scaffold(
        bottomBar = {
            BottomBar(
                selectedIndex = selectedTab,
                onItemSelected = { selectedTab = it }
            )
        },
    ){
        innerPadding ->

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Conteúdo principal
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Olá\n$userName!",
                        fontSize = 24.sp,
                        modifier = Modifier.weight(1f)
                    )

                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color.Gray)
                    ){
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Perfil",
                            tint = Color.White,
                            modifier = Modifier.padding(8.dp).align(Alignment.Center)
                        )
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color.Gray)
                    ){
                        IconButton(onClick = { showMenu = !showMenu }) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Perfil",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(Color.Gray, CircleShape)
                                    .padding(8.dp)
                            )
                        }

                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Editar perfil") },
                                onClick = {
                                    /*TODO: Navegar para tela de edição */
                                    showMenu = false
                                }
                            )

                            Divider(modifier = Modifier.padding(vertical = 4.dp).height(2.dp))

                            DropdownMenuItem(
                                text = { Text("Sair") },
                                onClick = {
                                    viewModel.logout()
                                    showMenu = false
                                    onExit()
                                }
                            )
                        }
                    }

                }

                Spacer(modifier = Modifier.height(8.dp))

                SalesCard { goMakeSale() }

                Text(
                    text = "Meu negócio",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.Start).padding(start = 16.dp, bottom = 8.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState())
                ) {

                    listofFeatures.forEach { feature ->
                        FeatureCard(
                            image = feature.image,
                            feature = feature.title,
                            goToFeature = feature.action
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Atividades do dia",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)

                    )

                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color.Transparent),
                        contentAlignment = Alignment.Center
                    ){
                        Icon(
                            imageVector = Icons.Default.CalendarMonth,
                            contentDescription = "Calendar",
                            tint = Color.Black,
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {

                    items(mockCards) { mock ->
                        DashboardCardItem(mock)
                    }
                }

//                Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.TopCenter,
//                ) {
//                    Column(
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Info,
//                            modifier = Modifier.size(120.dp),
//                            contentDescription = "searchoff",
//                            tint = Color.Gray,
//                        )
//
//                        Text(
//                            text = "Nenhuma atividade encontrada.",
//                            fontWeight = FontWeight.Bold,
//                            fontSize = 16.sp,
//                            textAlign = TextAlign.Center,
//                            modifier = Modifier.padding(top = 20.dp)
//                        )
//                    }
//                }
            }
        }
    }
}