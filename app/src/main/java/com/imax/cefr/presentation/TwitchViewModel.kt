package com.imax.cefr.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imax.cefr.core.base.resource.Resource
import com.imax.cefr.core.base.result.Status
import com.imax.cefr.data.models.twitch.live.TwitchLiveData
import com.imax.cefr.data.models.twitch.login.TwitchLoginData
import com.imax.cefr.data.models.twitch.video.TwitchVideoData
import com.imax.cefr.domain.use_case.TwitchUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class TwitchViewModel(private val usecase: TwitchUseCase): ViewModel() {

    private val _userVideosFlow = MutableSharedFlow<Resource<TwitchVideoData>>()
    internal val userVideosFlow get() = _userVideosFlow.asSharedFlow()

    fun getAllStreamsByUserId(userId: String)  = viewModelScope.launch {
        _userVideosFlow.emit(Resource.Loading)
        usecase.getAllVideosByUserId(userId).also { resultModel ->
            when(resultModel.status){
                Status.SUCCESS -> resultModel.data?.let { data ->
                    _userVideosFlow.emit(Resource.Success(data))
                }
                Status.ERROR -> resultModel.errorThrowable?.let { error ->
                    _userVideosFlow.emit(Resource.Error(error))
                }
            }
        }
    }

    private val _loginUserFlow = MutableSharedFlow<Resource<TwitchLoginData>>()
    internal val loginFlow get() = _loginUserFlow.asSharedFlow()

    fun loginUser(channelUsername: String) = viewModelScope.launch {
        _loginUserFlow.emit(Resource.Loading)
        usecase.loginUser(channelUsername).also { resultModel ->
            when(resultModel.status){
                Status.SUCCESS -> resultModel.data?.let { data ->
                    _loginUserFlow.emit(Resource.Success(data))
                }
                Status.ERROR -> resultModel.errorThrowable?.let { error ->
                    _loginUserFlow.emit(Resource.Error(error))
                }
            }
        }
    }

    private val _liveUserFlow = MutableSharedFlow<Resource<List<TwitchLiveData>>>()
    internal val liveUserFlow get() = _liveUserFlow.asSharedFlow()

    fun getLive(userIds: List<String>) = viewModelScope.launch {
        _liveUserFlow.emit(Resource.Loading)

        val liveDataList = mutableListOf<TwitchLiveData>()
        // Launch concurrent requests for each user
        val deferredResults = userIds.map { userId ->
            async {
                usecase.getLive(userId)
            }
        }
        // Await all results
        val results = deferredResults.awaitAll()

        results.forEach { model ->
            when (model.status) {
                Status.SUCCESS -> model.data?.let { data ->
                    liveDataList.add(data)
                }
                Status.ERROR -> model.errorThrowable?.let { error ->
                    _liveUserFlow.emit(Resource.Error(error))
                    return@launch // Exit if any error occurs
                }
            }
        }

        // Emit all collected live data
        if (liveDataList.isNotEmpty()) {
            _liveUserFlow.emit(Resource.Success(liveDataList.toList()))
        } else {
            _liveUserFlow.emit(Resource.Error(Throwable("No live streams found")))
        }

    }

}
