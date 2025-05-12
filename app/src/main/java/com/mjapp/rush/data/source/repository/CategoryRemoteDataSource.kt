package com.mjapp.rush.data.source.repository

import com.mjapp.rush.data.model.category.CategoryDataResponse
import com.mjapp.rush.data.remote.ApiService
import com.mjapp.rush.data.remote.CategoryDataRequest
import retrofit2.Response

class CategoryRemoteDataSource(private val apiService: ApiService) {
    val customerChannelSample = "Michael"
    suspend fun getCategories(merchantToken: String, branchUuid: String): Response<CategoryDataResponse> {
        return apiService.getCategories(merchantToken, CategoryDataRequest(branchUuid = branchUuid, customerChannel = customerChannelSample))
    }
}