package org.sales.management.auth.presentation.create

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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import managementsalesapp.composeapp.generated.resources.Res
import managementsalesapp.composeapp.generated.resources.img
import managementsalesapp.composeapp.generated.resources.login
import org.jetbrains.compose.resources.painterResource

@Composable
fun CreateUserScreen(
    onLogin: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(Res.drawable.img),
                contentDescription = null,
                modifier = Modifier.size(160.dp)
            )

            Text(
                text = "Gerencie\nmelhor seu\nnegócio agora!",
                fontWeight = FontWeight.Bold,
                fontSize = 44.sp,
                textAlign = TextAlign.Start,
                lineHeight = 40.sp,
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Nome da empresa") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Seu Nome") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("E-mail") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Senha") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Confirmar Senha") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onLogin,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF66A06F))
            ) {
                Text("Criar Conta")
            }
        }
    }
}