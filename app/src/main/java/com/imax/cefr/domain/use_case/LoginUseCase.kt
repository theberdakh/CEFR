package com.imax.cefr.domain.use_case

import com.imax.cefr.core.base.result.ResultModel
import com.imax.cefr.data.models.login.LoginRequestData
import com.imax.cefr.data.models.login.LoginResponseData

interface LoginUseCase {
    suspend fun invoke(loginRequestData: LoginRequestData): ResultModel<LoginResponseData>
}
