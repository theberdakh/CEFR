package com.imax.cefr.data.api.user

import com.imax.cefr.data.models.login.StudentResponseData
import com.imax.cefr.data.models.user.TeacherUserData
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {

    @GET("/users/me")
    suspend fun getTeacherInfo(): Response<TeacherUserData>

    @GET("/users/me")
    suspend fun getStudentInfo(): Response<StudentResponseData>
}
