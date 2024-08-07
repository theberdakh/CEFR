package com.imax.cefr.domain.use_case

import com.imax.cefr.core.base.result.ResultModel
import com.imax.cefr.data.models.LoginRequestData
import com.imax.cefr.data.models.TokenPayloadData

interface LoginUseCase {
    suspend fun invoke(loginRequestData: LoginRequestData): ResultModel<TokenPayloadData>
}
