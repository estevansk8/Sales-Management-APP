package org.sales.management.sales.presentation.form.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ionspin.kotlin.bignum.decimal.BigDecimal
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color
import org.sales.management.core.ui.composables.FormsButton


@Composable
fun TotalFooter(
    totalAmount: BigDecimal,
    onSubmit: () -> Unit,
    isSubmitting: Boolean
) {
    Surface(elevation = 4.dp) {
        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("TOTAL", style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold)
                Text("R$ ${totalAmount.toPlainString()}", style = MaterialTheme.typography.h5, fontWeight = FontWeight.Bold) // Formate a moeda
            }
            Spacer(Modifier.height(16.dp))

            Button(
                onClick = onSubmit,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF66A06F),
                    contentColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth(),
                enabled = !isSubmitting && totalAmount > BigDecimal.ZERO
            ) {
                if (isSubmitting) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colors.onPrimary)
                } else {
                    Text("SALVAR VENDA")
                }
            }
        }
    }
}