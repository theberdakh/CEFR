package com.example.cefr.domain.useCase.impl

import com.example.cefr.data.models.LoginRequestData
import com.example.cefr.data.models.TokenPayloadData
import com.example.cefr.domain.repository.LoginRepository
import com.example.cefr.domain.useCase.LoginUseCase

class LoginUseCaseImpl(private val repo: LoginRepository) : LoginUseCase {
    override suspend fun execute(body: LoginRequestData): Result<TokenPayloadData> {
        return repo.login(body)
    }
}
