package com.imax.cefr.data.models.login

import com.google.gson.annotations.SerializedName

data class UserResponseData(
    val id: String,
    val name: String,
    val email: String,
    @SerializedName("tel_number")
    val telNumber: String,
    val role: String,
    val channelName: String,
    val userName: String,
    val streamKey: String
)

