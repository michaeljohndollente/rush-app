package com.mjapp.rush.presentation.screen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.mjapp.rush.core.common.DataState
import com.mjapp.rush.data.model.product.ProductDataResponse
import com.mjapp.rush.data.model.product.ProductItem
import com.mjapp.rush.domain.model.Category
import com.mjapp.rush.presentation.viewmodel.ProductListViewModel

@Composable
fun ProductListScreen(viewModel: ProductListViewModel = hiltViewModel(), navController: NavController) {
    val categoriesState by viewModel.categories.collectAsState(initial = DataState.Loading)
    val productListState by viewModel.productList.collectAsState(initial = DataState.Loading)
    var selectedCategory by remember { mutableStateOf<Category?>(null) }
    val cartItemCount = remember { mutableStateOf(0) }

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
            StoreInfoSection("4F, SM Megamall, Mandaluyong City, Metro Manila", onMoreInfoClick = { })
            Spacer(modifier = Modifier.height(16.dp))

            CategoryDropdown(
                categoriesState = categoriesState,
                onCategorySelected = { category ->
                    selectedCategory = category
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "What's New",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            when (productListState) {
                is DataState.Loading -> CircularProgressIndicator(modifier = Modifier.padding(16.dp).align(alignment = Alignment.CenterHorizontally))
                is DataState.Success -> {
                    ProductGrid(products = (productListState as DataState.Success<ProductDataResponse>).data.list.orEmpty(), columns = 2, onItemClick = { productId -> navController.navigate("productDetail/$productId") })
                }
                is DataState.Error -> Text(text = (productListState as DataState.Error).message, color = MaterialTheme.colorScheme.error, modifier = Modifier.padding(16.dp))
            }
        }
    }
}

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

@Composable
fun StoreInfoSection(address: String, onMoreInfoClick: () -> Unit) {
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

            color = Color(255, 165, 0), // Use primary color for better visibility of clickable text
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

@Composable
fun ProductGrid(products: List<ProductItem>, columns: Int, onItemClick: (String) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(columns),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products.size) { index ->
            val product = products[index]
            ProductGridItem(product = product, onClick = { onItemClick(product.uuid!!) })
        }
    }
}

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

@Composable
fun CategoryDropdown(
    categoriesState: DataState<List<Category>>,
    onCategorySelected: (Category?) -> Unit,
    defaultText: String = "What's New"
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedCategory by remember { mutableStateOf<Category?>(null) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { expanded = !expanded }
    ) {
        Text(
            text = selectedCategory?.name ?: defaultText,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
        )
        Icon(Icons.Filled.ArrowDropDown, contentDescription = "View Categories")
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(
            onClick = {
                selectedCategory = null // User selected "What's New"
                expanded = false
                onCategorySelected(null)
            }, text = { Text("What's New") }
        )
        if (categoriesState is DataState.Success) {
            val categories = categoriesState.data
            Log.d("CategoryDropdown", "Current categoriesState: $categories")

            Log.d("CategoryDropdown", "Number of fetched categories: ${categories.size}")
            Log.d("CategoryDropdown", "Fetched categories: ${categories.map { it.name ?: "null" }}")
            categories.forEach { category ->
                DropdownMenuItem(
                    onClick = {
                        selectedCategory = category // User selected a specific category
                        expanded = false
                        onCategorySelected(category)
                    },
                    text = { Text(category.name ?: "Unknown Category") }
                )
            }
        } else if (categoriesState is DataState.Error) {
            Text("Error loading categories")
        } else {
            Text("Loading categories...")
        }
    }
}

