package com.mjapp.rush.data.source.repository

import com.mjapp.rush.data.source.local.CategoryDao
import com.mjapp.rush.data.source.local.CategoryEntity

class CategoryLocalDataSource(private val categoryDao: CategoryDao) {
    suspend fun getCachedCategories(): List<CategoryEntity> = categoryDao.getAll()
    suspend fun saveCategories(categories: List<CategoryEntity>) {
        categoryDao.deleteAll()
        categoryDao.insertAll(categories)
    }
}