package com.mjapp.rush.presentation.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun CartBottomBar(navController: NavController, cartItemCount: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,

    ) {
        Button(onClick = { navController.navigate("cart") }, shape = RoundedCornerShape(8.dp)) {
            Text(text = "View Cart ($cartItemCount items)")
        }
    }
}
