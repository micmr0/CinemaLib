package com.micmr0.cinemalib.themoviedb

import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor(val tokenManager: TokenManager) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        val newRequest = request
            .newBuilder()
            .header("Authorization", "Bearer ${tokenManager.getToken()}")
            .build()
        return chain.proceed(newRequest)
    }
}