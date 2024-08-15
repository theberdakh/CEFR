package com.imax.cefr.data.repository.user

import com.imax.cefr.core.base.result.ResultModel
import com.imax.cefr.core.base.source.BaseDataSource
import com.imax.cefr.data.api.user.UserApi
import com.imax.cefr.data.models.login.StudentResponseData
import com.imax.cefr.data.models.user.TeacherUserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl(private val api: UserApi): UserRepository, BaseDataSource() {

    override suspend fun getTeacherInfo(): ResultModel<TeacherUserData>  = invokeRequest {
            withContext(Dispatchers.IO){
                api.getTeacherInfo()
            }
        }

    override suspend fun getStudentInfo(): ResultModel<StudentResponseData> =
        invokeRequest {
            withContext(Dispatchers.IO){
                api.getStudentInfo()
            }
        }
}
