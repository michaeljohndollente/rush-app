package com.mjapp.rush.data.repository

import com.mjapp.rush.data.model.entities.Product
import com.mjapp.rush.domain.repository.ProductRepository
import com.mjapp.rush.domain.usecase.SaveProductListUseCase

class SaveProductListUseCaseImpl(private val productRepository: ProductRepository) : SaveProductListUseCase {
    override suspend fun invoke(products: List<Product>) = productRepository.saveProductList(products)
}