package com.example.cefr.utils

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun getColor(context: Context, color: Int) = ContextCompat.getColor(context, color)