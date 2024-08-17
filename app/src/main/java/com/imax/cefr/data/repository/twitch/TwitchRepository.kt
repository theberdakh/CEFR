package com.imax.cefr.data.repository.twitch

import com.imax.cefr.core.base.result.ResultModel
import com.imax.cefr.data.models.twitch.TwitchLoginData
import com.imax.cefr.data.models.twitch.TwitchVideoData

interface TwitchRepository {
    suspend fun getStreamsByUserId(userId: String): ResultModel<TwitchVideoData>
    suspend fun loginUser(channelUserName: String): ResultModel<TwitchLoginData>
}
