package com.imax.cefr.data.api.twitch

import com.imax.cefr.data.models.twitch.TwitchLoginData
import com.imax.cefr.data.models.twitch.TwitchVideoData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface TwitchApi {

    @GET("/helix/videos")
    suspend fun getStreamsByUserId(
        @Query("user_id") userId: String
    ): Response<TwitchVideoData>

    @GET("/helix/users")
    suspend fun loginUser(
        @Query("login") channelUsername: String)
    :Response<TwitchLoginData>
}

