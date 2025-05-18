package com.mjapp.rush.data.source.remote

import com.mjapp.rush.core.network.ApiService
import com.mjapp.rush.domain.model.auth.TokenResponse
import com.mjapp.rush.domain.model.category.CategoryDataResponse
import com.mjapp.rush.domain.model.product.ProductDataResponse
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getMerchantToken(): TokenResponse = apiService.getMerchantToken()

    suspend fun getCategories(merchantToken: String, branchUuid: String): CategoryDataResponse {
        return apiService.getCategories(merchantToken, mapOf("customer_channel" to "MOBILE_APP", "branch_uuid" to branchUuid))
    }

    suspend fun getProductList(merchantToken: String, page: Int, branchUuid: String, brandUuid: String): ProductDataResponse {
        return apiService.getProductList(merchantToken, page, mapOf("branch_uuid" to branchUuid, "brand_uuid" to brandUuid))
    }

}