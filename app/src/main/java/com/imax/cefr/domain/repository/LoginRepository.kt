package com.imax.cefr.domain.repository

import com.imax.cefr.data.models.LoginRequestData
import com.imax.cefr.data.models.TokenPayloadData

interface LoginRepository {
    suspend fun login(body: LoginRequestData): Result<TokenPayloadData>
}
