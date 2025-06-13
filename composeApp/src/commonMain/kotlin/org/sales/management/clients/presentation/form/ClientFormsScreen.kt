package org.sales.management.clients.presentation.form

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import androidx.compose.runtime.remember
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import managementsalesapp.composeapp.generated.resources.Res
import managementsalesapp.composeapp.generated.resources.login
import org.jetbrains.compose.resources.painterResource
import org.sales.management.core.ui.composables.TopBar
import org.sales.management.core.ui.composables.FormsButton
import org.sales.management.core.ui.composables.InputField


@Composable
fun ClientFormsScreen(
    goBack: () -> Unit,
    viewModel: ClientFormsViewModel = koinViewModel()
) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

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
                title = "Cadastro\nde Cliente",
                onBack = { goBack() },
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
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(Res.drawable.login),
                contentDescription = null,
                modifier = Modifier.size(300.dp)
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
                        label = "Telefone",
                        value = phone,
                        onValueChange = { phone = it },
                        keyboardType = KeyboardType.Phone
                    )

                    InputField(
                        label = "Endereço",
                        value = address,
                        onValueChange = { address = it },
                        keyboardType = KeyboardType.Text
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    FormsButton(
                        text = "Cadastrar",
                        onClick = {
                            viewModel.saveClient(name, phone, address)
                        }
                    )

                }
            }
        }

    }
}
