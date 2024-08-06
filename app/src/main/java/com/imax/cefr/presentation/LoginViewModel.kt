package com.imax.cefr.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imax.cefr.data.models.LoginRequestData
import com.imax.cefr.data.models.TokenPayloadData
import com.imax.cefr.domain.useCase.LoginUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class LoginViewModel(private val useCase: LoginUseCase) : ViewModel() {

    private val _loginResult = MutableSharedFlow<Result<TokenPayloadData>>()
    val loginResult: Flow<Result<TokenPayloadData>> get() = _loginResult

    fun login(body: LoginRequestData) {
        viewModelScope.launch {
            _loginResult.emit(useCase.execute(body))
        }
    }
}
