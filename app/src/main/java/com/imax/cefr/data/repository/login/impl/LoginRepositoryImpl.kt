package com.imax.cefr.data.repository.login.impl

import com.imax.cefr.core.base.source.BaseDataSource
import com.imax.cefr.data.models.login.LoginRequestData
import com.imax.cefr.data.api.auth.TwitchEduAuthApi
import com.imax.cefr.data.repository.login.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRepositoryImpl(private val api: TwitchEduAuthApi) : LoginRepository, BaseDataSource() {

    override suspend fun login(body: LoginRequestData) =
        invokeRequest {
            withContext(Dispatchers.IO) {
                api.login(body)
            }
        }
}
