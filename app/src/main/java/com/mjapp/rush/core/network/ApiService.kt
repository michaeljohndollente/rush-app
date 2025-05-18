package com.mjapp.rush.core.network

import com.mjapp.rush.domain.model.auth.TokenResponse
import com.mjapp.rush.domain.model.category.CategoryDataResponse
import com.mjapp.rush.domain.model.product.ProductDataResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("merchant-service/token/customer-app")
    suspend fun getMerchantToken(
        @Body credentials: Map<String, String> = mapOf(
            "key" to "3wccQJodsNwf",
            "secret" to "vU9S48S7cp3hHratLWcmeo2DkyOWTC6X"
        )
    ): TokenResponse

    @POST("estore-service/v1/public/customer/estore/category/branch/list")
    suspend fun getCategories(
        @Header("x-merchant-token") merchantToken: String,
        @Body request: Map<String, String>
    ): CategoryDataResponse

    @POST("estore-service/v2/public/customer/estore/product/list/{page}")
    suspend fun getProductList(
        @Header("x-merchant-token") merchantToken: String,
        @Path("page") page: Int,
        @Body request: Map<String, String>
    ): ProductDataResponse

    companion object {
        const val BASE_URL = "https://proxy.staging.api-rush.loyaltyco.xyz/"
    }
}
