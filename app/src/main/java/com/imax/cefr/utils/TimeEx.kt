package com.imax.cefr.utils

import android.annotation.SuppressLint
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@SuppressLint("NewApi")
fun addMinutes(minutes: CharSequence?, afterMinutes: Long): String {
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    // Parse the input time string to a LocalTime object
    val time = LocalTime.parse(minutes, timeFormatter)

    // Add the minutes to the time
    val newTime = time.plusMinutes(afterMinutes)

    // Return the new time formatted in HH:mm
    return newTime.format(timeFormatter)
}
