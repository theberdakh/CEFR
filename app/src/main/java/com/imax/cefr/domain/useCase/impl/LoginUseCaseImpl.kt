package com.imax.cefr.domain.useCase.impl

import com.imax.cefr.data.models.LoginRequestData
import com.imax.cefr.data.models.TokenPayloadData
import com.imax.cefr.domain.repository.LoginRepository
import com.imax.cefr.domain.useCase.LoginUseCase

class LoginUseCaseImpl(private val repo: LoginRepository) : LoginUseCase {
    override suspend fun execute(body: LoginRequestData): Result<TokenPayloadData> {
        return repo.login(body)
    }
}
