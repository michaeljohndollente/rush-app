package com.mjapp.rush.domain.usecase

import com.mjapp.rush.data.model.entities.Category
import com.mjapp.rush.data.model.category.CategoryDataResponse

interface GetCategoriesUseCase {
    suspend operator fun invoke(): Result<List<Category>>
}