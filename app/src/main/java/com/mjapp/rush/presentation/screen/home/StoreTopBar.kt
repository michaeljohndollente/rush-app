package com.mjapp.rush.presentation.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun StoreTopBar(storeName: String, category: String) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .padding(bottom = 16.dp, top = 32.dp)
    ) {
        Text(text = storeName, style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
        Text(text = category, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
    }
}