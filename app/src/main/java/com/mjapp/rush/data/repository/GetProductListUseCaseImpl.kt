package com.mjapp.rush.data.repository

import com.mjapp.rush.data.model.entities.Product
import com.mjapp.rush.domain.repository.ProductRepository
import com.mjapp.rush.domain.usecase.GetProductListUseCase

class GetProductListUseCaseImpl(private val productRepository: ProductRepository) : GetProductListUseCase {
    override suspend fun invoke(page: Int): Result<Pair<List<Product>, Int?>> = productRepository.getProductList(page)
}