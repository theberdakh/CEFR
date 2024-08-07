package com.imax.cefr.data.api

import com.imax.cefr.data.PathConstants.ADMIN_LOGIN
import com.imax.cefr.data.PathConstants.APPLICATION_JSON
import com.imax.cefr.data.PathConstants.HEADER_ACCEPT
import com.imax.cefr.data.PathConstants.HEADER_CONTENT_TYPE
import com.imax.cefr.data.models.LoginRequestData
import com.imax.cefr.data.models.TokenPayloadData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface CefrApi {

    @POST(ADMIN_LOGIN)
    suspend fun login(
        @Body body: LoginRequestData
    ): Response<TokenPayloadData>
}
