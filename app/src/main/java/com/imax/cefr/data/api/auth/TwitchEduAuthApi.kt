package com.imax.cefr.data.api.auth

import com.imax.cefr.data.models.login.LoginRequestData
import com.imax.cefr.data.models.login.LoginResponseData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface TwitchEduAuthApi {

    @POST("auth/login")
    suspend fun login(
        @Body body: LoginRequestData
    ): Response<LoginResponseData>
}
