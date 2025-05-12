package com.mjapp.rush.domain.usecase

import com.mjapp.rush.data.model.entities.Product

interface SaveProductListUseCase {
    suspend operator fun invoke(products: List<Product>)
}