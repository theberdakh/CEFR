package com.imax.cefr.domain.use_case

import com.imax.cefr.core.base.result.ResultModel
import com.imax.cefr.data.models.twitch.TwitchLoginData
import com.imax.cefr.data.models.twitch.TwitchVideoData

interface TwitchUseCase {
    suspend fun getAllVideosByUserId(userId: String): ResultModel<TwitchVideoData>
    suspend fun loginUser(channelUsername: String): ResultModel<TwitchLoginData>
}
