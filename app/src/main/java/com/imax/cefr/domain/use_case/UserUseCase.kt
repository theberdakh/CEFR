package com.imax.cefr.domain.use_case

import com.imax.cefr.core.base.result.ResultModel
import com.imax.cefr.data.models.login.StudentResponseData
import com.imax.cefr.data.models.user.TeacherUserData

interface UserUseCase {
    suspend fun getTeacherInfo(): ResultModel<TeacherUserData>

    suspend fun getStudentInfo(): ResultModel<StudentResponseData>
}
