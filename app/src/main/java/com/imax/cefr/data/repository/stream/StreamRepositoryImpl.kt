package com.imax.cefr.data.repository.stream

import com.imax.cefr.core.base.result.ResultModel
import com.imax.cefr.core.base.source.BaseDataSource
import com.imax.cefr.data.api.streams.StreamsApi
import com.imax.cefr.data.models.stream.CreateStreamRequestData
import com.imax.cefr.data.models.stream.CreateStreamResponseData
import com.imax.cefr.data.models.stream.all.AllStreamsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class StreamRepositoryImpl(private val api: StreamsApi) : StreamRepository, BaseDataSource() {
    override suspend fun createStream(body: CreateStreamRequestData) =
        invokeRequest {
            withContext(Dispatchers.IO){
                api.createStream(body)
            }
        }

    override suspend fun getAllStream(): ResultModel<AllStreamsResponse>  =
        invokeRequest {
            withContext(Dispatchers.IO){
                api.getAllStreams()
            }
        }
}
