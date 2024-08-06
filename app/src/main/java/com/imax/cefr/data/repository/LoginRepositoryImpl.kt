package com.imax.cefr.data.repository

import com.imax.cefr.data.models.LoginRequestData
import com.imax.cefr.data.models.TokenPayloadData
import com.imax.cefr.data.remote.CefrApi
import com.imax.cefr.domain.repository.LoginRepository
import com.imax.cefr.utils.ErrorObjectMessage

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
