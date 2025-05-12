package com.mjapp.rush.data.remote

import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    private var merchantToken: String? = null

    suspend fun getOrFetchToken(): String? {
        if (merchantToken.isNullOrBlank()) {
            val response = RetrofitClient.apiService.getMerchantToken()
            merchantToken = response.body()?.token
        }
        return merchantToken
    }

    override  fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        runBlocking {
            getOrFetchToken()?.let { token ->
                requestBuilder.addHeader("x-merchant-token", token)
            }
        }
        return chain.proceed(requestBuilder.build())
    }
}