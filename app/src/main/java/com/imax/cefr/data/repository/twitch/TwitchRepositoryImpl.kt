package com.imax.cefr.data.repository.twitch

import android.util.Log
import com.imax.cefr.core.base.source.BaseDataSource
import com.imax.cefr.data.api.twitch.TwitchApi
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
           val a =  api.loginUser(channelUsername = userName)
            Log.d("TwitchRepositoryImpl", "Response: $a")

            api.loginUser(channelUsername = userName)
        }
    }
}
