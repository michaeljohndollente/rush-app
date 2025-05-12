package com.mjapp.rush.domain.usecase

import com.mjapp.rush.data.model.entities.Category

interface SaveCategoriesUseCase {
    suspend operator fun invoke(categories: List<Category>)
}