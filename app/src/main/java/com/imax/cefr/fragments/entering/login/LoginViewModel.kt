package com.imax.cefr.fragments.entering.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imax.cefr.core.base.resource.Resource
import com.imax.cefr.core.base.result.Status
import com.imax.cefr.data.models.login.LoginRequestData
import com.imax.cefr.data.models.login.LoginResponseData
import com.imax.cefr.domain.use_case.LoginUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val useCase: LoginUseCase) : ViewModel() {

    private val _loginFlow = MutableSharedFlow<Resource<LoginResponseData>>()
    internal val loginFlow: Flow<Resource<LoginResponseData>> get() = _loginFlow.asSharedFlow()

    fun login(loginRequestData: LoginRequestData) {

        viewModelScope.launch {

            _loginFlow.emit(Resource.Loading)
            useCase.invoke(loginRequestData).also { result ->


                Log.d("LoginViewModel", "${result.status}")


                when (result.status) {
                    Status.SUCCESS -> {
                        result.data?.let { loginResponseData ->
                            Log.d("LoginViewModel", "token: ${loginResponseData.token}")
                            _loginFlow.emit(Resource.Success(loginResponseData))
                        }
                    }

                    Status.ERROR -> {
                        result.errorThrowable?.let { error ->
                            Log.d("LoginViewModel", "error: $error")
                            _loginFlow.emit(Resource.Error(error))
                        }
                    }
                }
            }
        }
    }
}
