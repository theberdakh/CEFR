package com.imax.cefr.domain.use_case.impl

import com.imax.cefr.core.base.result.ResultModel
import com.imax.cefr.data.models.login.LoginRequestData
import com.imax.cefr.data.models.login.LoginResponseData
import com.imax.cefr.data.repository.login.LoginRepository
import com.imax.cefr.domain.use_case.LoginUseCase

class LoginUseCaseImpl(private val repo: LoginRepository) : LoginUseCase {
    override suspend fun invoke(loginRequestData: LoginRequestData): ResultModel<LoginResponseData> {
        val response = repo.login(loginRequestData)

        return ResultModel(
            status =response.status,
            data = response.data,
            errorThrowable = response.errorThrowable
        )
    }
}
