package com.mjapp.rush.data.repository

import com.mjapp.rush.data.model.entities.Product
import com.mjapp.rush.domain.repository.ProductRepository
import com.mjapp.rush.domain.usecase.GetCachedProductListUseCase

class GetCachedProductListUseCaseImpl(private val productRepository: ProductRepository) : GetCachedProductListUseCase {
    override suspend fun invoke(): List<Product> = productRepository.getCachedProductList()
}