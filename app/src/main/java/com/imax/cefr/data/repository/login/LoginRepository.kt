package com.imax.cefr.data.repository.login

import com.imax.cefr.core.base.result.ResultModel
import com.imax.cefr.data.models.LoginRequestData
import com.imax.cefr.data.models.TokenPayloadData

interface LoginRepository {
    suspend fun login(body: LoginRequestData): ResultModel<TokenPayloadData>
}
