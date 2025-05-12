package com.mjapp.rush.data.repository

import com.mjapp.rush.data.model.entities.Category
import com.mjapp.rush.domain.repository.CategoryRepository
import com.mjapp.rush.domain.usecase.GetCategoriesUseCase

class GetCategoriesUseCaseImpl(private val categoryRepository: CategoryRepository) : GetCategoriesUseCase {
    override suspend fun invoke(): Result<List<Category>> = categoryRepository.getCategories()
}