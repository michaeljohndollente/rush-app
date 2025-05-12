package com.mjapp.rush.domain.repository

import com.mjapp.rush.data.model.entities.Category

interface CategoryRepository {
    suspend fun getCategories(): Result<List<Category>>
    suspend fun saveCategories(categories: List<Category>)
    suspend fun getCachedCategories(): List<Category>
}