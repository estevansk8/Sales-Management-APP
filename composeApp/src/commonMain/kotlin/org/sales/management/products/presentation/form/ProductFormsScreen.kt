package org.sales.management.products.presentation.form

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import managementsalesapp.composeapp.generated.resources.Res
import managementsalesapp.composeapp.generated.resources.img
import managementsalesapp.composeapp.generated.resources.login
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.sales.management.core.ui.composables.TopBar
import org.sales.management.core.ui.composables.FormsButton
import org.sales.management.core.ui.composables.InputField


@Composable
fun ProductFormsScreen(
    goBack: () -> Unit,
    viewModel: ProductFormsViewModel = koinViewModel()
) {
    var name by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("") }
    var stock by remember { mutableStateOf(0) }


    Scaffold(
        topBar = {
            TopBar(
                title = "Cadastro\nde Produto",
                onBack = { goBack() },
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(Res.drawable.img),
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )

            Surface(
                shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                color = Color.LightGray,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    InputField(
                        label = "Nome",
                        value = name,
                        onValueChange = { name = it },
                        keyboardType = KeyboardType.Text
                    )

                    InputField(
                        label = "Preço",
                        value = price,
                        onValueChange = { price = it },
                        keyboardType = KeyboardType.Decimal
                    )

                    InputField(
                        label = "Estoque",
                        value = stock.toString(),
                        onValueChange = { stock = it.toIntOrNull() ?: 0 },
                        keyboardType = KeyboardType.Number
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    FormsButton(
                        text = "Cadastrar",
                        onClick = { viewModel.saveProduct(name, price, stock)}
                    )
                }
            }
        }

//                    Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(padding)
//                .padding(16.dp),
//            verticalArrangement = Arrangement.Center,
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            OutlinedTextField(
//                value = name,
//                onValueChange = { name = it },
//                label = { Text("Nome") },
//                modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
//            )
//
//            OutlinedTextField(
//                value = price,
//                onValueChange = { price = it },
//                label = { Text("Preço") },
//                modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
//            )
//
//            OutlinedTextField(
//                value = stock.toString(),
//                onValueChange = { stock = it.toIntOrNull() ?: 0 },
//                label = { Text("Estoque") },
//                modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
//                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))
//
//            FormsButton(
//                text = "Cadastrar",
//                onClick = {
//                    viewModel.saveProduct(name, price, stock)
//                }
//            )
//        }
    }
}
