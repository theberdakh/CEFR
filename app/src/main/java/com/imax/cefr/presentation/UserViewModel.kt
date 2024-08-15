package com.imax.cefr.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imax.cefr.core.base.resource.Resource
import com.imax.cefr.core.base.result.Status
import com.imax.cefr.data.models.login.StudentResponseData
import com.imax.cefr.data.models.user.TeacherUserData
import com.imax.cefr.domain.use_case.UserUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class UserViewModel(private val userUseCase: UserUseCase): ViewModel() {

    private val _studentFlow = MutableSharedFlow<Resource<StudentResponseData>>()
    internal val studentFlow: Flow<Resource<StudentResponseData>> get() = _studentFlow.asSharedFlow()

    fun getStudentInfo() = viewModelScope.launch {
        _studentFlow.emit(Resource.Loading)
        userUseCase.getStudentInfo().also { result ->
            Log.d("UserViewModel", "${result.status}")
            when (result.status) {
                Status.SUCCESS -> {
                    result.data?.let { studentResponseData ->
                        Log.d("LoginViewModel", " ${studentResponseData}")
                        _studentFlow.emit(Resource.Success(studentResponseData))
                    }
                }

                Status.ERROR -> {
                    result.errorThrowable?.let { error ->
                        Log.d("LoginViewModel", "error: ${error.printStackTrace()}")
                        _studentFlow.emit(Resource.Error(error))
                    }
                }
            }
        }
    }

    private val _teacherFlow = MutableSharedFlow<Resource<TeacherUserData>>()
    internal val teacherFlow: Flow<Resource<TeacherUserData>> get() = _teacherFlow.asSharedFlow()

    fun getTeacherInfo() = viewModelScope.launch {
        _studentFlow.emit(Resource.Loading)
        userUseCase.getTeacherInfo().also { result ->
            when(result.status){
                Status.SUCCESS -> result.data?.let {
                    _teacherFlow.emit(Resource.Success(it))
                }
                Status.ERROR -> result.errorThrowable?.let {
                    _teacherFlow.emit(Resource.Error(it))
                }
            }
        }
    }

}
