package com.imax.cefr.domain.use_case

import com.imax.cefr.core.base.result.ResultModel
import com.imax.cefr.data.models.twitch.login.TwitchLoginData
import com.imax.cefr.data.models.twitch.video.TwitchVideoData

interface TwitchUseCase {
    suspend fun getAllVideosByUserId(userId: String): ResultModel<TwitchVideoData>
    suspend fun loginUser(channelUsername: String): ResultModel<TwitchLoginData>
}
