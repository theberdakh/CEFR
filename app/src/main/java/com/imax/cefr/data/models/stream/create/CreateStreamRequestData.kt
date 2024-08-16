package com.imax.cefr.data.models.stream.create

data class CreateStreamRequestData(
    val streamTitle: String,
    val description: String,
    val endTime: String,
    val group: String,
    val startTime: String,
    val status: String,
    val teacherId: String,
    val teacherName: String
)

