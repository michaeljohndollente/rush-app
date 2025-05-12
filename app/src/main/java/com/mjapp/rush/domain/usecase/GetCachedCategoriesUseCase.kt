package com.mjapp.rush.domain.usecase

import com.mjapp.rush.data.model.entities.Category

interface GetCachedCategoriesUseCase {
    suspend operator fun invoke(): List<Category>
}