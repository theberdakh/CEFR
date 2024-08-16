package com.imax.cefr.domain.use_case.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.imax.cefr.core.base.result.ResultModel
import com.imax.cefr.data.models.stream.CreateStreamRequestData
import com.imax.cefr.data.models.stream.CreateStreamResponseData
import com.imax.cefr.data.models.stream.StreamResponseData
import com.imax.cefr.data.models.stream.all.AllStreamsPagingSource
import com.imax.cefr.data.models.stream.all.AllStreamsResponse
import com.imax.cefr.data.repository.stream.StreamRepository
import com.imax.cefr.domain.use_case.StreamUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class StreamUseCaseImpl(private val repo: StreamRepository) : StreamUseCase {
    override suspend fun createStream(createStreamRequestData: CreateStreamRequestData):
            ResultModel<CreateStreamResponseData> {

        val response = repo.createStream(createStreamRequestData)
        return ResultModel(
            status = response.status,
            data = response.data,
            errorThrowable = response.errorThrowable
        )
    }

    override suspend fun getAllStream(limit: Int): Flow<PagingData<StreamResponseData>> {

        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory =  {
                AllStreamsPagingSource{
                    repo.getAllStream(it, limit)
                }
            }).flow
        }
    }

