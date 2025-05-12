package com.mjapp.rush.data.repository

import com.mjapp.rush.data.model.entities.Category
import com.mjapp.rush.domain.repository.CategoryRepository
import com.mjapp.rush.domain.usecase.GetCachedCategoriesUseCase

class GetCachedCategoriesUseCaseImpl(private val categoryRepository: CategoryRepository) : GetCachedCategoriesUseCase {
    override suspend fun invoke(): List<Category> = categoryRepository.getCachedCategories()
}