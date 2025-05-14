package com.mjapp.rush.presentation.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.mjapp.rush.data.model.product.ProductItem

@Composable
fun ProductGrid(products: List<ProductItem>, columns: Int, onItemClick: (String) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products.size) { index ->
            val product = products[index]
            ProductGridItem(product = product, onClick = {
                onItemClick(product.uuid!!)
            })
        }
    }
}