package com.imax.cefr.data.remote

import com.imax.cefr.data.models.LoginRequestData
import com.imax.cefr.data.models.TokenPayloadData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface CefrApi {

    @Headers(
        "Content-Type: application/json",
        "Accept: application/json"
    )
    @POST("/api/admin/login")
    suspend fun login(@Body body: LoginRequestData): Response<TokenPayloadData>
}
