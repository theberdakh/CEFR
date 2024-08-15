package com.imax.cefr.domain.use_case

import com.imax.cefr.core.base.result.ResultModel
import com.imax.cefr.data.models.login.LoginRequestData
import com.imax.cefr.data.models.login.LoginResponseData
import com.imax.cefr.data.models.stream.CreateStreamRequestData
import com.imax.cefr.data.models.stream.CreateStreamResponseData

interface StreamUseCase {
    suspend fun createStream(createStreamRequestData: CreateStreamRequestData): ResultModel<CreateStreamResponseData>
}
