package com.imax.cefr.data.repository.user

import com.imax.cefr.core.base.result.ResultModel
import com.imax.cefr.data.models.login.StudentResponseData
import com.imax.cefr.data.models.user.TeacherUserData

interface UserRepository {
    suspend fun getTeacherInfo(): ResultModel<TeacherUserData>

    suspend fun getStudentInfo(): ResultModel<StudentResponseData>
}
