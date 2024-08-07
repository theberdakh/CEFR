package com.imax.cefr.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imax.cefr.core.base.resource.Resource
import com.imax.cefr.core.base.result.Status
import com.imax.cefr.data.models.LoginRequestData
import com.imax.cefr.data.models.TokenPayloadData
import com.imax.cefr.domain.use_case.LoginUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val useCase: LoginUseCase) : ViewModel() {

    private val _loginFlow = MutableSharedFlow<Resource<TokenPayloadData>>()
    val loginFlow: Flow<Resource<TokenPayloadData>> get() = _loginFlow.asSharedFlow()

    fun login(loginRequestData: LoginRequestData) = viewModelScope.launch {
        useCase.invoke(loginRequestData).also {
            when (it.status) {
                Status.SUCCESS -> it.data?.let { result ->
                    _loginFlow.emit(Resource.Success(result))
                }
                Status.ERROR -> it.errorThrowable?.let { error ->
                    _loginFlow.emit(Resource.Error(error))
                }
            }
        }
    }
}
