package com.imax.cefr.domain.use_case.impl

import com.imax.cefr.core.base.result.ResultModel
import com.imax.cefr.data.models.LoginRequestData
import com.imax.cefr.data.models.TokenPayloadData
import com.imax.cefr.data.repository.login.LoginRepository
import com.imax.cefr.domain.use_case.LoginUseCase

class LoginUseCaseImpl(private val repo: LoginRepository) : LoginUseCase {
    override suspend fun invoke(loginRequestData: LoginRequestData): ResultModel<TokenPayloadData> {
        val response = repo.login(loginRequestData)
        return ResultModel(
            status =response.status,
            data = response.data,
            errorThrowable = response.errorThrowable
        )
    }
}
