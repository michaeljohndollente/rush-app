package com.mjapp.rush.presentation.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mjapp.rush.data.model.product.ProductItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductGridItem(product: ProductItem, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        elevation = CardDefaults.elevatedCardElevation(),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.height(220.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = product.images?.firstOrNull()?.image_name ?: "",
                contentDescription = product.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = product.name ?: "Product Name",
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                textAlign = TextAlign.Center
            )
            Text(
                text = "₱ ${product.price ?: "Price Unavailable"}",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold
            )
        }
    }
}