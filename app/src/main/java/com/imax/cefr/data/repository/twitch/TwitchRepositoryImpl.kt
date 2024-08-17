package com.imax.cefr.data.repository.twitch

import android.util.Log
import com.imax.cefr.core.base.result.ResultModel
import com.imax.cefr.core.base.source.BaseDataSource
import com.imax.cefr.data.api.twitch.TwitchApi
import com.imax.cefr.data.models.twitch.live.TwitchLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TwitchRepositoryImpl(private val api: TwitchApi): BaseDataSource(), TwitchRepository {

    override suspend fun getStreamsByUserId(userId: String) = invokeRequest {
        withContext(Dispatchers.IO){
            api.getStreamsByUserId(userId)
        }
    }

    override suspend fun loginUser(userName: String) = invokeRequest {
        withContext(Dispatchers.IO){
            api.loginUser(channelUsername = userName)
        }
    }

    override suspend fun getLive(userId: String) = invokeRequest {
        withContext(Dispatchers.IO) {
            api.getLive(userId)
        }
    }
}
