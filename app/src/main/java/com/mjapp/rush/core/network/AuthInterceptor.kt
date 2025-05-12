package com.mjapp.rush.core.network

import android.util.Log
import okhttp3.Interceptor

class AuthInterceptor(private val tokenProvider: suspend () -> String?) : Interceptor {
    override fun intercept(chain: Interceptor.Chain) = chain.proceed(
        chain.request().newBuilder()
            .apply {
                val token = runCatching {
                    kotlinx.coroutines.runBlocking { tokenProvider() }
                }.getOrNull()
                token?.let {
                    header("x-merchant-token", it)
                    Log.d("AuthInterceptor", "Adding token: $it to ${chain.request().url}")
                } ?: Log.d("AuthInterceptor", "Token is null")
            }
            .build()
    )
}