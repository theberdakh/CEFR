package com.imax.cefr.domain.use_case.impl

import android.util.Log
import com.imax.cefr.core.base.result.ResultModel
import com.imax.cefr.data.models.twitch.TwitchLoginData
import com.imax.cefr.data.models.twitch.TwitchVideoData
import com.imax.cefr.data.repository.twitch.TwitchRepository
import com.imax.cefr.domain.use_case.TwitchUseCase

class TwitchUseCaseImpl(private val repository: TwitchRepository) : TwitchUseCase {

    override suspend fun getAllVideosByUserId(userId: String): ResultModel<TwitchVideoData> {
        val response = repository.getStreamsByUserId(userId)

        Log.d("TwitchUseCaseImpl", "Response: ${response.data.toString()}")
        Log.d("TwitchUseCaseImpl", "Error: ${response.errorThrowable}")
        Log.d("TwitchUseCaseImpl", "Status: ${response.status}")

        return ResultModel(
            status = response.status,
            data = response.data,
            errorThrowable = response.errorThrowable
        )
    }

    override suspend fun loginUser(channelUsername: String): ResultModel<TwitchLoginData> {
        val response = repository.loginUser(channelUsername)
        return ResultModel(
            status = response.status,
            data = response.data,
            errorThrowable = response.errorThrowable
        )
    }
}
