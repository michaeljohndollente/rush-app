package com.mjapp.rush.domain.repository

import com.mjapp.rush.data.model.entities.Product

interface ProductRepository {
    suspend fun getProductList(page: Int): Result<Pair<List<Product>, Int?>>
    suspend fun saveProductList(products: List<Product>)
    suspend fun getCachedProductList(): List<Product>
}