package com.example.cefr.domain.useCase

import com.example.cefr.data.models.LoginRequestData
import com.example.cefr.data.models.TokenPayloadData

interface LoginUseCase {
    suspend fun execute(body: LoginRequestData): Result<TokenPayloadData>
}