package com.imax.cefr.data.utils

import com.imax.cefr.data.pref.LocalStorage
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
