package com.imax.cefr.data.models.stream

enum class LiveStreamStatus(val status: String) {
    PLANNED("planned"), LIVE("live"), ENDED("ended")
}
