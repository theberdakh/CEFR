package com.imax.cefr.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imax.cefr.core.base.resource.Resource
import com.imax.cefr.core.base.result.Status
import com.imax.cefr.data.models.twitch.login.TwitchLoginData
import com.imax.cefr.data.models.twitch.video.TwitchVideoData
import com.imax.cefr.domain.use_case.TwitchUseCase
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
}
