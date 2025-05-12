package com.mjapp.rush.presentation.screen

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.mjapp.rush.data.model.entities.Category
import com.mjapp.rush.data.model.entities.Product
import com.mjapp.rush.presentation.factory.ProductListViewModelFactory
import com.mjapp.rush.presentation.viewmodel.ProductListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductListScreen(
    onProductClick: (Product) -> Unit,
    onCartClick: () -> Unit,
    viewModel: ProductListViewModel = viewModel(factory = ProductListViewModelFactory(LocalContext.current.applicationContext as Application))
) {
    val categories by viewModel.categories.collectAsState()
    val products by viewModel.products.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(title = { Text("Static Store Name") }, actions = {
            IconButton(onClick = onCartClick) {
                Icon(Icons.Filled.ShoppingCart, contentDescription = "Cart")
            }
        })

        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (errorMessage != null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error: $errorMessage", textAlign = TextAlign.Center)
            }
        } else {
            LazyColumn {
                item {
                    // "What's New" Category
                    if (categories.isNotEmpty()) {
                        CategorySection(category = categories.first()) // Assuming only one "What's New" category
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

//                items(products.lastIndex) { product ->
//                    ProductItemCard(product = Product(
//                    ), onClick = { onProductClick(product) })
//                    Divider()
//                }

                if (products.isNotEmpty() && viewModel.maxPage != null && viewModel.currentPage < viewModel.maxPage!!) {
                    item {
                        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                            Button(onClick = { viewModel.loadMoreProducts() }) {
                                Text("Load More")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CategorySection(category: Category) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = category.name, style = MaterialTheme.typography.bodySmall)
        // You might add a "View All" button if viewAllEnabled is true
    }
}

@Composable
fun ProductItemCard(product: Product, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = product.imageUrl,
            contentDescription = product.name,
            modifier = Modifier.size(80.dp),
            placeholder = painterResource(id = android.R.drawable.ic_menu_gallery),
            error = painterResource(id = android.R.drawable.ic_menu_report_image),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = product.name, style = MaterialTheme.typography.bodyLarge)
            Text(text = product.price, style = MaterialTheme.typography.bodyMedium)
        }
    }
}