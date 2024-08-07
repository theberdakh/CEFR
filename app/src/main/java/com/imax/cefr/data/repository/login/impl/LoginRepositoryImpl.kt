package com.imax.cefr.data.repository.login.impl

import com.imax.cefr.core.base.result.ResultModel
import com.imax.cefr.core.base.source.BaseDataSource
import com.imax.cefr.data.models.LoginRequestData
import com.imax.cefr.data.models.TokenPayloadData
import com.imax.cefr.data.api.CefrApi
import com.imax.cefr.data.repository.login.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepositoryImpl(private val api: CefrApi) : LoginRepository, BaseDataSource() {
    override suspend fun login(body: LoginRequestData) =
        invokeRequest {
            withContext(Dispatchers.IO) {
                api.login(body)
            }
        }
}
