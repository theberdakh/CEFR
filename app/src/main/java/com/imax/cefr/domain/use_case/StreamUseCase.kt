package com.imax.cefr.domain.use_case

import androidx.paging.PagingData
import com.imax.cefr.core.base.result.ResultModel
import com.imax.cefr.data.models.login.LoginRequestData
import com.imax.cefr.data.models.login.LoginResponseData
import com.imax.cefr.data.models.stream.CreateStreamRequestData
import com.imax.cefr.data.models.stream.CreateStreamResponseData
import com.imax.cefr.data.models.stream.StreamResponseData
import com.imax.cefr.data.models.stream.all.AllStreamsResponse
import kotlinx.coroutines.flow.Flow

interface StreamUseCase {
    suspend fun createStream(createStreamRequestData: CreateStreamRequestData): ResultModel<CreateStreamResponseData>
    suspend fun getAllStream(limit: Int): Flow<PagingData<StreamResponseData>>
}
