package com.example.cefr.data.remote.interceptors

import com.example.cefr.utils.LocalStorage
import okhttp3.Interceptor

class AccessTokenInterceptor(private val localStorage: LocalStorage) : Interceptor {

    override fun intercept(chain: Interceptor.Chain) = chain.proceed(
        chain.request().newBuilder()
            .addHeader(
                "Authorization",
                "Bearer ${localStorage.token}"
            ).build()
    )
}