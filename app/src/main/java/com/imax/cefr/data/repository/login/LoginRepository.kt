package com.imax.cefr.data.repository.login

import com.imax.cefr.core.base.result.ResultModel
import com.imax.cefr.data.models.login.LoginRequestData
import com.imax.cefr.data.models.TokenPayloadData
import com.imax.cefr.data.models.login.LoginResponseData

interface LoginRepository {
    suspend fun login(body: LoginRequestData): ResultModel<LoginResponseData>
}
