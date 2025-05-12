package com.mjapp.rush.data.repository

import com.mjapp.rush.data.model.entities.Category
import com.mjapp.rush.domain.repository.CategoryRepository
import com.mjapp.rush.domain.usecase.SaveCategoriesUseCase

class SaveCategoriesUseCaseImpl(private val categoryRepository: CategoryRepository) : SaveCategoriesUseCase {
    override suspend fun invoke(categories: List<Category>) = categoryRepository.saveCategories(categories)
}