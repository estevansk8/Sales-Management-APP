package org.sales.management.core.ui.itens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.text.font.FontWeight


@Composable
fun CustomTopBar(
    title: String,
    onBack: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .height(72.dp),
        title = {
            Text(
                text = title,
                color = Color.Black,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    tint = Color.Black,
                    contentDescription = "Voltar",
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        actions = {
            IconButton(
                onClick = {  },
                modifier = Modifier.padding(end = 16.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color.Gray)
                        .padding(8.dp)
                ) {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Perfil",
                            tint = Color.White,
                            modifier = Modifier
                                .size(40.dp)
                                .background(Color.Gray, CircleShape)
                        )
                    }
                }
            }
        },
        backgroundColor = Color.White,
        elevation = 0.dp,
    )
}
