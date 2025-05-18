package com.mjapp.rush.presentation.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mjapp.rush.core.common.DataState
import com.mjapp.rush.data.model.product.ProductDataResponse
import com.mjapp.rush.domain.model.Category
import com.mjapp.rush.presentation.viewmodel.ProductListViewModel

@Composable
fun ProductListScreen (
    viewModel: ProductListViewModel = hiltViewModel(),
    navController: NavController
) {
    val categoriesState by viewModel.categories.collectAsState(initial = DataState.Loading)
    val productListState by viewModel.productList.collectAsState(initial = DataState.Loading)
    var selectedCategory by remember { mutableStateOf<Category?>(null) }
    val cartItemCount = remember { mutableStateOf(2) }

    Scaffold(
        modifier = Modifier
            .padding(8.dp)
            .padding(horizontal = 16.dp)
            .fillMaxSize(),
        topBar = { StoreTopBar("Wings & Co. - SM Megamall", "Fastfood, Breakfast") },
        bottomBar = {
                if (cartItemCount.value > 0) {
                CartBottomBar(navController = navController, cartItemCount = cartItemCount.value)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            StoreInfoSection("4F, SM Megamall, Mandaluyong City, Metro Manila")

            Spacer(modifier = Modifier.height(16.dp))

            CategoryDropdown(
                categoriesState = categoriesState,
                onCategorySelected = { category ->
                    selectedCategory = category
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            when (productListState) {
                is DataState.Loading -> CircularProgressIndicator(modifier = Modifier
                    .padding(16.dp)
                    .align(alignment = Alignment.CenterHorizontally))
                is DataState.Success -> {
                    ProductGrid(products = (
                            productListState as DataState.Success<ProductDataResponse>
                            )
                        .data.list.orEmpty(),
                        columns = 2,
                        onItemClick = {
                            productId -> navController.navigate("productDetail/$productId")
                    })
                }
                is DataState.Error -> Text(text = (productListState as DataState.Error).message, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(16.dp))
            }
        }
    }
}
