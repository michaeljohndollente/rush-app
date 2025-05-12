package com.mjapp.rush.data.remote

import com.mjapp.rush.data.model.product.ProductDataResponse
import com.mjapp.rush.data.model.category.CategoryDataResponse
import com.mjapp.rush.data.model.auth.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("merchant-service/token/customer-app")
    suspend fun getMerchantToken(
        @Field("key") key: String = "3wccQJodsNwf",
        @Field("secret") secret: String = "vU9S48S7cp3hHratLWcmeo2DkyOWTC6X"
    ): Response<TokenResponse>

    @POST("estore-service/v1/public/customer/estore/category/branch/list")
    suspend fun getCategories(
        @Header("x-merchant-token") merchantToken: String,
        @Body request: CategoryDataRequest,
//        @Field("customer_channel") customerChannel: String = "Michael",
//        @Field("branch_uuid") branchUuid: String = "f1c0ded3-ff8b-435e-b5b3-bd17cba34a51"
    ): Response<CategoryDataResponse>

    @POST("estore-service/v4/public/customer/estore/product/list/{page}")
    suspend fun getProductList(
        @Header("x-merchant-token") merchantToken: String,
        @Path("page") page: Int,
        @Body request: ProductDataRequest,
//        @Field("branch_uuid") branchUuid: String = "f1c0ded3-ff8b-435e-b5b3-bd17cba34a51",
//        @Field("brand_uuid") brandUuid: String = "ef89d985-2e2a-4eb8-8553-84867c9affe7"
    ): Response<ProductDataResponse>
}

data class TokenRequest(
    val key: String,
    val secret: String
)

data class CategoryDataRequest(
    val customerChannel: String,
    val branchUuid: String
)

data class ProductDataRequest(
    val branchUuid: String,
    val brandUuid: String
)