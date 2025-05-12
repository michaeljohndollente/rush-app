package com.mjapp.rush.domain.usecase

import com.mjapp.rush.data.model.entities.Product
import com.mjapp.rush.data.model.product.ProductDataResponse

interface GetProductListUseCase {
    suspend operator fun invoke(page: Int): Result<Pair<List<Product>, Int?>>
}