package com.example.cefr.data.repository

import com.example.cefr.data.models.LoginRequestData
import com.example.cefr.data.models.TokenPayloadData
import com.example.cefr.data.remote.CefrApi
import com.example.cefr.domain.repository.LoginRepository
import com.example.cefr.utils.ErrorObjectMessage

class LoginRepositoryImpl(private val api: CefrApi) : LoginRepository {
    override suspend fun login(body: LoginRequestData): Result<TokenPayloadData> {
        return try {
            val response = api.login(body)
            val responseBody = response.body()
            if (response.isSuccessful && responseBody != null) {
                Result.success(responseBody)
            } else {
                val message = ErrorObjectMessage.getErrorObjectMessage(response.errorBody())
                Result.failure(Throwable(message))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
