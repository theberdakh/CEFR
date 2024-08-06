package com.imax.cefr.domain.useCase

import com.imax.cefr.data.models.LoginRequestData
import com.imax.cefr.data.models.TokenPayloadData

interface LoginUseCase {
    suspend fun execute(body: LoginRequestData): Result<TokenPayloadData>
}
