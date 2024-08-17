package com.imax.cefr.data.repository.twitch

import com.imax.cefr.core.base.result.ResultModel
import com.imax.cefr.data.models.twitch.live.TwitchLiveData
import com.imax.cefr.data.models.twitch.login.TwitchLoginData
import com.imax.cefr.data.models.twitch.video.TwitchVideoData

interface TwitchRepository {
    suspend fun getStreamsByUserId(userId: String): ResultModel<TwitchVideoData>
    suspend fun loginUser(channelUserName: String): ResultModel<TwitchLoginData>
    suspend fun getLive(userId: String): ResultModel<TwitchLiveData>
}
