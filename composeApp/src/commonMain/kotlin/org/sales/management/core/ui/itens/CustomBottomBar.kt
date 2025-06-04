package org.sales.management.core.ui.itens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomBottomBar(
    selectedIndex: Int = 0,
    onItemSelected: (Int) -> Unit
) {
    val icons = listOf(
        Icons.Default.Home,
        Icons.Default.BarChart,
        Icons.Default.Settings
    )

    val activeColor = Color(0xFF66A06F) // Verde
    val inactiveColor = Color(0xFF2D2D2D) // Cinza escuro
    val iconActive = Color.Black
    val iconInactive = Color.Gray

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .background(Color.Black, shape = RoundedCornerShape(40.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            icons.forEachIndexed { index, icon ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(if (index == selectedIndex) activeColor else inactiveColor)
                        .clickable { onItemSelected(index) },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = if (index == selectedIndex) iconActive else iconInactive
                    )
                }
            }
        }
    }
}
