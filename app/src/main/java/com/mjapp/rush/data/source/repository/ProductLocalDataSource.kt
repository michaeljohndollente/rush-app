package com.mjapp.rush.data.source.repository

import com.mjapp.rush.data.source.local.ProductDao
import com.mjapp.rush.data.source.local.ProductEntity

class ProductLocalDataSource(private val productDao: ProductDao) {
    suspend fun getCachedProductList(): List<ProductEntity> = productDao.getAll()
    suspend fun saveProductList(products: List<ProductEntity>) {
        productDao.deleteAll()
        productDao.insertAll(products)
    }
}