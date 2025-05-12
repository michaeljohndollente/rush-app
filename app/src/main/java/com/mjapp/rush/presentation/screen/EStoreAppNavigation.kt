package com.mjapp.rush.presentation.screen

//import ProductViewScreen
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun EStoreAppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "productList") {
        composable(route = "productList") {
            ProductListScreen(
                onProductClick = { product ->
                    navController.navigate("productView/${product.uuid}")
                },
                onCartClick = { navController.navigate("cart") }
            )
        }
        composable(
            route = "productView/{productUuid}",
            arguments = listOf(navArgument("productUuid") { type = NavType.StringType })
        ) { navBackStackEntry ->
            val productUuid = navBackStackEntry.arguments?.getString("productUuid")
//            if (!productUuid.isNullOrEmpty()) {
//                ProductViewScreen(
//                    navBackStackEntry = navBackStackEntry,
//                    onAddToCart = { product, variations ->
//                        // Handle adding to cart (you might want to pass data to a shared ViewModel)
//                        navController.navigate("cart") // For now, navigate to cart
//                    },
//                    onClose = { navController.popBackStack() }
//                )
//            } else {
//                // Handle error or navigate back if productUuid is missing
//                Text("Error: Product UUID not found")
//            }
        }
//        composable(route = "cart") {
//            CartScreen(
//                onEditItem = { cartItem ->
//                    navController.navigate("productView/${cartItem.product.uuid}")
//                },
//                onBackToProducts = { navController.popBackStack() }
//            )
//        }
    }
}