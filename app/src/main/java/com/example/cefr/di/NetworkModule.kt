package com.example.cefr.di

import com.example.cefr.data.remote.interceptors.AccessTokenInterceptor
import com.example.cefr.data.remote.interceptors.CefrApi
import com.example.cefr.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {

    single {
        getCefrService(retrofit = get())
    }

    single<Retrofit> {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val interceptor = AccessTokenInterceptor(localStorage = get())

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(interceptor)
            .readTimeout(40, TimeUnit.SECONDS)
            .writeTimeout(40, TimeUnit.SECONDS)
            .build()

        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .build()
    }
}

fun getCefrService(retrofit: Retrofit): CefrApi {
    return retrofit.create(CefrApi::class.java)
}