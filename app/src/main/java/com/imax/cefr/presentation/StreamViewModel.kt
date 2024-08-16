package com.imax.cefr.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.imax.cefr.core.base.resource.Resource
import com.imax.cefr.core.base.result.ResultModel
import com.imax.cefr.core.base.result.Status
import com.imax.cefr.data.models.login.StudentResponseData
import com.imax.cefr.data.models.stream.CreateStreamRequestData
import com.imax.cefr.data.models.stream.CreateStreamResponseData
import com.imax.cefr.data.models.stream.StreamResponseData
import com.imax.cefr.data.models.stream.all.AllStreamsResponse
import com.imax.cefr.domain.use_case.StreamUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class StreamViewModel(private val useCase: StreamUseCase): ViewModel() {

    private val _createStreamFlow = MutableSharedFlow<Resource<CreateStreamResponseData>>()
    internal val createStreamFlow: Flow<Resource<CreateStreamResponseData>> get() = _createStreamFlow.asSharedFlow()


    fun createStream(createStreamRequestData: CreateStreamRequestData) = viewModelScope.launch {
        _createStreamFlow.emit(Resource.Loading)
        useCase.createStream(createStreamRequestData).also { resultModel ->
            when(resultModel.status){
                Status.SUCCESS -> resultModel.data?.let { data ->
                    _createStreamFlow.emit(Resource.Success(data))
                }

                Status.ERROR -> resultModel.errorThrowable?.let { error ->
                    _createStreamFlow.emit(Resource.Error(error))
                }
            }
        }
    }

    private val _allStreamFlow: MutableSharedFlow<Flow<PagingData<StreamResponseData>>> = MutableSharedFlow()
    internal val allStreamFlow get() = _allStreamFlow.asSharedFlow()

    fun getAllStream(limit: Int) = viewModelScope.launch {
        useCase.getAllStream(limit).cachedIn(viewModelScope).also {
            _allStreamFlow.emit(it)
        }
    }

}
