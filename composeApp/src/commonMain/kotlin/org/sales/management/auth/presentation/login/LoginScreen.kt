package org.sales.management.auth.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material3.Surface


import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import managementsalesapp.composeapp.generated.resources.Res
import managementsalesapp.composeapp.generated.resources.compose_multiplatform
import managementsalesapp.composeapp.generated.resources.login
import org.jetbrains.compose.resources.painterResource

@Composable
fun LoginScreen(
    onLogin: () -> Unit,
    onSignUp: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(Res.drawable.login),
            contentDescription = null,
            modifier = Modifier.size(300.dp)
        )

        Surface(
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            color = Color.White,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Bem vindo\nEmpreendedor!",
                    fontWeight = FontWeight.Bold,
                    fontSize = 44.sp,
                    textAlign = TextAlign.Start,
                    lineHeight = 52.sp,
                )

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("E-mail") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Senha") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onLogin,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF66A06F))
                ) {
                    Text("Login")
                }
                Text(
                    text = buildAnnotatedString {
                        append("Não tem uma conta? ")
                        withStyle(style = SpanStyle(color = Color(0xFF66A06F), fontWeight = FontWeight.Bold)) {
                            append("Criar conta")
                        }
                    },
                    color = Color(0xFF66A06F),
                    modifier = Modifier.padding(top = 16.dp).clickable { onSignUp() }
                )
            }
        }
    }
}
