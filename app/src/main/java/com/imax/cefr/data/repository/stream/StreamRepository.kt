package com.imax.cefr.data.repository.stream

import com.imax.cefr.core.base.result.ResultModel
import com.imax.cefr.data.models.stream.CreateStreamRequestData
import com.imax.cefr.data.models.stream.CreateStreamResponseData
import com.imax.cefr.data.models.stream.all.AllStreamsResponse

interface StreamRepository {
    suspend fun createStream(body: CreateStreamRequestData): ResultModel<CreateStreamResponseData>
    suspend fun getAllStream(page: Int, limit: Int): ResultModel<AllStreamsResponse>
}
