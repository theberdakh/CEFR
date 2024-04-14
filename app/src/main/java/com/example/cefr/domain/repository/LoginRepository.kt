package com.example.cefr.domain.repository

import com.example.cefr.data.models.LoginRequestData
import com.example.cefr.data.models.TokenPayloadData

interface LoginRepository {
    suspend fun login(body: LoginRequestData): Result<TokenPayloadData>
}