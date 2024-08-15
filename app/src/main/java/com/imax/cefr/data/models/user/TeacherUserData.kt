package com.imax.cefr.data.models.user

import com.google.gson.annotations.SerializedName

data class TeacherUserData(
    @SerializedName("_id")
    val id: String,
    val channelName: String,
    val email: String,
    val name: String,
    val role: String,
    val streamKey: String,
    @SerializedName("tel_number")
    val telNumber: String,
    val userName: String,
    @SerializedName("__v")
    val v: Int,
)
