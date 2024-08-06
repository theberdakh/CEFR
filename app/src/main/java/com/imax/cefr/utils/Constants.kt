package com.imax.cefr.utils

import com.imax.cefr.R
import com.imax.cefr.data.models.GridData

object Constants {

    const val BASE_URL = "https://aral-boyi.uz"

    val gridImageList = listOf(
        GridData(
            0,
            R.drawable.ic_add_1,
            "Confidence",
            "With speaking lessons, you will speak in a\nshort time"
        ),
        GridData(
            1,
            R.drawable.ic_add_2,
            "Patience",
            "Take your time to learn. Develop a habit and\nmake it a part of your daily life"
        ),
        GridData(
            2,
            R.drawable.ic_add_3,
            "Modern Security",
            "We will provide you with the best and most\nmodern security"
        ),
    )
}
