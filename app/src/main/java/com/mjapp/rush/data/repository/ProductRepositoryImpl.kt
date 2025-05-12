package com.mjapp.rush.data.repository

import com.mjapp.rush.core.toDomain
import com.mjapp.rush.core.toEntity
import com.mjapp.rush.data.model.entities.Product
import com.mjapp.rush.data.source.repository.ProductLocalDataSource
import com.mjapp.rush.data.source.repository.ProductRemoteDataSource
import com.mjapp.rush.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val remoteDataSource: ProductRemoteDataSource,
    private val localDataSource: ProductLocalDataSource
) : ProductRepository {
    override suspend fun getProductList(page: Int): Result<Pair<List<Product>, Int?>> {
        return try {
            val response = remoteDataSource.fetchProductList(page)
            if (response.isSuccessful) {
                val productItems = response.body()?.listData
                val maxPage = response.body()?.maxPage
                productItems?.let {
                    val products = it.map { it.toDomain() }
                    if (page == 1) { // Only cache the first page for simplicity in this example
                        localDataSource.saveProductList(products.map { it.toEntity() })
                    }
                    Result.success(Pair(products, maxPage))
                } ?: Result.failure(Exception(response.body()?.message ?: "Empty product list"))
            } else {
                Result.failure(Exception(response.errorBody()?.string() ?: "Failed to fetch product list"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun saveProductList(products: List<Product>) {
        localDataSource.saveProductList(products.map { it.toEntity() })
    }

    override suspend fun getCachedProductList(): List<Product> {
        return localDataSource.getCachedProductList().map { it.toDomain() }
    }
}