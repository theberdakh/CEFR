package com.imax.cefr.data.repository.stream

import com.imax.cefr.core.base.result.ResultModel
import com.imax.cefr.data.models.stream.CreateStreamRequestData
import com.imax.cefr.data.models.stream.CreateStreamResponseData

interface StreamRepository {
    suspend fun createStream(body: CreateStreamRequestData): ResultModel<CreateStreamResponseData>
}
