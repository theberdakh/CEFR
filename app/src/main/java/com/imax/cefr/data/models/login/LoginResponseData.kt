package com.imax.cefr.data.models.login

data class LoginResponseData(
    val message: String,
    val token: String,
    val user: UserResponseData
)


