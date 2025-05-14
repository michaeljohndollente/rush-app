package com.mjapp.rush.data.source.local

import com.mjapp.rush.data.source.local.dao.CategoryDao
import com.mjapp.rush.data.source.local.dao.ProductDao
import com.mjapp.rush.data.source.local.entity.CategoryEntity
import com.mjapp.rush.data.source.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val categoryDao: CategoryDao,
    private val productDao: ProductDao
) {
    suspend fun insertCategories(categories: List<CategoryEntity>) = categoryDao.insertCategories(categories)
    fun getAllCategories(): Flow<List<CategoryEntity>> = categoryDao.getAllCategories()
    suspend fun clearCategories() = categoryDao.clearCategories()

    suspend fun insertProducts(products: List<ProductEntity>) = productDao.insertProducts(products)

    fun getProducts(branchUuid: String, brandUuid: String, limit: Int, offset: Int): Flow<List<ProductEntity>> =
        productDao.getProducts(branchUuid, brandUuid, limit, offset)
    suspend fun clearProducts(branchUuid: String, brandUuid: String) = productDao.clearProducts(branchUuid, brandUuid)
}