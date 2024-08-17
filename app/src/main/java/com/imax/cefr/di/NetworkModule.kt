package com.imax.cefr.di

import com.imax.cefr.BuildConfig
import com.imax.cefr.core.base.interceptor.AccessTokenInterceptor
import com.imax.cefr.data.api.auth.TwitchEduAuthApi
import com.imax.cefr.core.base.constants.Constants
import com.imax.cefr.core.base.interceptor.TwitchInterceptor
import com.imax.cefr.data.api.streams.StreamsApi
import com.imax.cefr.data.api.twitch.TwitchApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val CEFR_RETROFIT = "CefrRetrofit"
const val TWITCH_RETROFIT = "TwitchRetrofit"
val networkModule = module {

    single {
        getCefrService(retrofit = get(named(CEFR_RETROFIT)))
    }

    single {
        getStreamApi(retrofit = get(named(CEFR_RETROFIT)))
    }

    single {
        getTwitchApi(retrofit = get(named(TWITCH_RETROFIT)))
    }

    single(named(TWITCH_RETROFIT)) {
        createRetrofit("https://api.twitch.tv", listOf(TwitchInterceptor()))
    }
    single(named(CEFR_RETROFIT)) {
        val interceptor = AccessTokenInterceptor(localStorage = get())
        createRetrofit(BuildConfig.BASE_URL, listOf(interceptor))
    }

}

fun getCefrService(retrofit: Retrofit): TwitchEduAuthApi {
    return retrofit.create(TwitchEduAuthApi::class.java)
}

fun getStreamApi(retrofit: Retrofit): StreamsApi {
    return retrofit.create(StreamsApi::class.java)
}

fun getTwitchApi(retrofit: Retrofit): TwitchApi {
    return retrofit.create(TwitchApi::class.java)
}

fun createRetrofit(
    baseUrl: String,
    httpClientInterceptors: List<Interceptor>
): Retrofit {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

    val okHttpClient = OkHttpClient.Builder()
        .apply {
            addInterceptor(httpLoggingInterceptor)
            httpClientInterceptors.forEach { addInterceptor(it) }
            readTimeout(Constants.NETWORK_READ_TIME_OUT, TimeUnit.SECONDS)
            writeTimeout(Constants.NETWORK_WRITE_TIME_OUT, TimeUnit.SECONDS)
        }
        .build()

    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build()
}

