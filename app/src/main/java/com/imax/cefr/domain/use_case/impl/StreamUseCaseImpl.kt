package com.imax.cefr.domain.use_case.impl

import com.imax.cefr.core.base.result.ResultModel
import com.imax.cefr.data.models.stream.CreateStreamRequestData
import com.imax.cefr.data.models.stream.CreateStreamResponseData
import com.imax.cefr.data.models.stream.all.AllStreamsResponse
import com.imax.cefr.data.repository.stream.StreamRepository
import com.imax.cefr.domain.use_case.StreamUseCase

class StreamUseCaseImpl(private val repo: StreamRepository) : StreamUseCase{
    override suspend fun createStream(createStreamRequestData: CreateStreamRequestData):
            ResultModel<CreateStreamResponseData> {

        val response = repo.createStream(createStreamRequestData)
        return ResultModel(
            status = response.status,
            data = response.data,
            errorThrowable = response.errorThrowable
        )
    }

    override suspend fun getAllStream(): ResultModel<AllStreamsResponse> {
        val response = repo.getAllStream()
        return ResultModel(
            status = response.status,
            data = response.data,
            errorThrowable = response.errorThrowable
        )
    }
}
