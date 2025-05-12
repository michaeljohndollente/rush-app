package com.mjapp.rush.data.source.repository

import com.mjapp.rush.data.model.product.ProductDataResponse
import com.mjapp.rush.data.remote.ApiService
import com.mjapp.rush.data.remote.ProductDataRequest
import retrofit2.Response

class ProductRemoteDataSource(private val apiService: ApiService) {
    suspend fun getProductList(merchantToken: String, page: Int, branchUuid: String, brandUuid: String): Response<ProductDataResponse> {
        return apiService.getProductList(merchantToken, page, ProductDataRequest(branchUuid = branchUuid, brandUuid = brandUuid))
    }
}