package com.imax.cefr.data.models.login

import com.google.gson.annotations.SerializedName

data class StudentResponseData(
    val id: String,
    val name: String,
    val email: String,
    @SerializedName("tel_number")
    val telNumber: String,
    val role: String,
    val group: String
)

