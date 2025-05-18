package com.mjapp.rush.domain.repository

import com.mjapp.rush.core.common.DataState
import com.mjapp.rush.domain.model.category.CategoryItem
import com.mjapp.rush.domain.model.product.ProductDataResponse
import kotlinx.coroutines.flow.Flow

interface StoreRepository {
    suspend fun getAuthToken(): String?
    fun getCategories(branchUuid: String): Flow<DataState<List<CategoryItem>>>
    fun getProductList(page: Int, branchUuid: String, brandUuid: String): Flow<DataState<ProductDataResponse>>
}
