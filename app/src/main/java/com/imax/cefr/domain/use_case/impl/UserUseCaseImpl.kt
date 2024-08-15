package com.imax.cefr.domain.use_case.impl

import com.imax.cefr.core.base.result.ResultModel
import com.imax.cefr.data.models.login.StudentResponseData
import com.imax.cefr.data.models.user.TeacherUserData
import com.imax.cefr.data.repository.user.UserRepository
import com.imax.cefr.domain.use_case.UserUseCase

class UserUseCaseImpl(private val repository: UserRepository): UserUseCase {

    override suspend fun getTeacherInfo(): ResultModel<TeacherUserData> {
        val response = repository.getTeacherInfo()

        return ResultModel(
            status =response.status,
            data = response.data,
            errorThrowable = response.errorThrowable
        )
    }

    override suspend fun getStudentInfo(): ResultModel<StudentResponseData> {
        val response = repository.getStudentInfo()

        return ResultModel(
            status =response.status,
            data = response.data,
            errorThrowable = response.errorThrowable
        )
    }
}
