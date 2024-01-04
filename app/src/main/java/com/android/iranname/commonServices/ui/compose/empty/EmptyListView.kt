package com.abtinandroidstdio.uritect.commonServices.ui.screen.empty

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmptyListView(icon : ImageVector , text : String) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 32.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier
                    .size(64.dp)
            )

            Spacer(
                modifier = Modifier
                .height(16.dp)
            )

            Text(
                text = text,
                color = Color.Gray,
                fontSize = 18.sp
            )
        }
    }
}