package com.imax.cefr.core.base.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class TwitchInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain) = chain.proceed(
        chain.request().newBuilder()
            .addHeader(
                "Client-ID",
                "0c1p2h1plfu58pw75o3wbut6ffscx4"
            ).addHeader(
                "Authorization",
                "Bearer 89m5dn3wghndhjxj9n21lb9mt71jvg"
            ).build()
    )
}
