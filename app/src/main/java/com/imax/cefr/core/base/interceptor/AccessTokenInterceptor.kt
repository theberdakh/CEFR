package com.imax.cefr.core.base.interceptor

import android.util.Log
import com.imax.cefr.core.base.pref.LocalStorage
import okhttp3.Interceptor

class AccessTokenInterceptor(private val localStorage: LocalStorage) : Interceptor {

    override fun intercept(chain: Interceptor.Chain) = chain.proceed(
        chain.request().newBuilder()
            .addHeader(
                "Authorization",
                "Bearer ${localStorage.getToken()}"
            ).build()
    )
}
