package com.mjapp.rush.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.mjapp.rush.data.model.product.ProductItem
import com.mjapp.rush.presentation.viewmodel.ProductDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailScreen(
    productId: ProductItem,
    navController: NavController,
    viewModel: ProductDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = { /* ... */ },
        bottomBar = { /* ... */ }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues).fillMaxSize().padding(16.dp)
        ) {
            item {
                uiState.productItem?.images?.firstOrNull()?.image_name?.let { imageUrl ->
                    AsyncImage(model = imageUrl, contentDescription = uiState.productItem?.name)
                }
                Text(text = uiState.productItem?.name ?: "")
                Text(text = uiState.productItem?.description ?: "")
                Text(text = "Price: ₱ ${uiState.productItem?.price ?: "0.00"}")
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(uiState.variations) { variationGroup ->
                item {
                    Text(text = variationGroup.groupName)
                }
                items(variationGroup.options) { option ->
                    Row(
                        modifier = Modifier.clickable { viewModel.selectVariation(variationGroup.groupName, option.name) },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = viewModel.selectedVariations.collectAsState().value[variationGroup.groupName] == option.name,
                            onClick = { viewModel.selectVariation(variationGroup.groupName, option.name) }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = option.name)
                    }
                }
                item { Spacer(modifier = Modifier.height(16.dp)) }
            }

            if (uiState.error != null) {
                item { Text(text = "Error: ${uiState.error}", color = MaterialTheme.colors.error) }
            }
        }
    }
}