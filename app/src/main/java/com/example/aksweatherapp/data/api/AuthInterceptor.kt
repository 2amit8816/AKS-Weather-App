package com.example.aksweatherapp.data.api

import com.example.aksweatherapp.common.TokenManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(private val tokenManager: TokenManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val token = tokenManager.getToken()
        val originalHttpUrl = chain.request().url
        //val url = originalHttpUrl.newBuilder().addQueryParameter("access_key", token).build()
        val url = originalHttpUrl.newBuilder().build()

        return chain.proceed(request.url(url).build())
    }
}