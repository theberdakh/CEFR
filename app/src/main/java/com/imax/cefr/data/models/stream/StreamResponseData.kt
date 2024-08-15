package com.imax.cefr.data.models.stream

import com.google.gson.annotations.SerializedName

data class StreamResponseData(
    @SerializedName("_id")
    val id: String,
    val streamTitle: String,
    val description: String,
    val startTime: String,
    val endTime: String,
    val group: String,
    val teacherId: String,
    val teacherName: String,
    val status: String
)
