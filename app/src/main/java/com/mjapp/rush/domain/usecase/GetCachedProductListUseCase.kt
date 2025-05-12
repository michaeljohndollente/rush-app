package com.mjapp.rush.domain.usecase

import com.mjapp.rush.data.model.entities.Product

interface GetCachedProductListUseCase {
    suspend operator fun invoke(): List<Product>
}