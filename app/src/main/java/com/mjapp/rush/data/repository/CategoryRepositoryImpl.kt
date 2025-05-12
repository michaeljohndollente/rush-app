package com.mjapp.rush.data.repository

import com.mjapp.rush.core.toDomain
import com.mjapp.rush.core.toEntity
import com.mjapp.rush.data.model.entities.Category
import com.mjapp.rush.data.source.repository.CategoryLocalDataSource
import com.mjapp.rush.data.source.repository.CategoryRemoteDataSource
import com.mjapp.rush.domain.repository.CategoryRepository

class CategoryRepositoryImpl(
    private val remoteDataSource: CategoryRemoteDataSource,
    private val localDataSource: CategoryLocalDataSource
) : CategoryRepository {
    override suspend fun getCategories(): Result<List<Category>> {
        return try {
            val response = remoteDataSource.fetchCategories()
            if (response.isSuccessful) {
                val categoryItems = response.body()?.categoryData?.categoryItem
                categoryItems?.let {
                    val categories = listOf(it.toDomain()) // Assuming only one "What's New" category
                    localDataSource.saveCategories(categories.map { it.toEntity() })
                    Result.success(categories)
                } ?: Result.failure(Exception(response.body()?.message ?: "Empty category data"))
            } else {
                Result.failure(Exception(response.errorBody()?.string() ?: "Failed to fetch categories"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun saveCategories(categories: List<Category>) {
        localDataSource.saveCategories(categories.map { it.toEntity() })
    }

    override suspend fun getCachedCategories(): List<Category> {
        return localDataSource.getCachedCategories().map { it.toDomain() }
    }
}