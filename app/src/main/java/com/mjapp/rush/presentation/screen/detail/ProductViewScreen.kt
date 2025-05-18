package com.mjapp.rush.presentation.screen.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProductViewScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .verticalScroll(rememberScrollState())
            .padding(bottom = 16.dp)
    ) {
        // Product Details
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Auntie's Aussie Beef Burger",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Experience flavor from the barbie with every bite! It's a carnivore's dream- succulent beef, layered with fresh veggies, and topped with our signature barbeque sauce.",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Choose your drink
            Text(
                text = "Choose your drink",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
            )
            Spacer(modifier = Modifier.height(8.dp))
            RadioButtonRow(label = "Coke", onSelect = { })
            RadioButtonRow(label = "Sprite", onSelect = {  })
            RadioButtonRow(label = "House Blend Iced Tea", onSelect = {  })
            RadioButtonRow(label = "Pepsi", onSelect = {  })

            Spacer(modifier = Modifier.height(16.dp))
            Divider()
            Spacer(modifier = Modifier.height(16.dp))

            // Price and Quantity
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "₱ 250",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = {  }) {
                        Text("-", style = MaterialTheme.typography.displaySmall, fontSize = 50.sp,)
                    }
                    Text(text = "2", style = MaterialTheme.typography.bodyLarge)
                    IconButton(onClick = {  }) {
                        Icon(Icons.Filled.Add, "Add")
                    }
                }
            }
            Spacer(modifier = Modifier.height(24.dp))

            // Add to Cart Button
            Button(
                onClick = {  },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF2994A))
            ) {
                Text(
                    text = "Add to Cart",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    ),
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}