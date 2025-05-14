package com.mjapp.rush.domain.repository

import com.mjapp.rush.core.common.DataState
import com.mjapp.rush.data.model.product.ProductDataResponse
import com.mjapp.rush.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface StoreRepository {
    suspend fun getAuthToken(): String?
    fun getCategories(branchUuid: String): Flow<DataState<List<Category>>>
    fun getProductList(page: Int, branchUuid: String, brandUuid: String): Flow<DataState<ProductDataResponse>>
}
