package com.mjapp.rush.presentation.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun StoreInfoSection(address: String) {
    var isAddressVisible by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(text = "Store Info", style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold))
        Text(
            text = if (isAddressVisible) "Less Info" else "More Info",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),

            color = Color(255, 165, 0),
            modifier = Modifier.clickable { isAddressVisible = !isAddressVisible }
        )
    }
    if (isAddressVisible) {
        Text(
            text = address,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
    Divider()
}