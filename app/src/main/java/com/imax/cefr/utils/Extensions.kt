package com.imax.cefr.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun Fragment.toastMessage(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Fragment.snackBar(message: String?) {
    Snackbar.make(requireView(), message ?: "", Snackbar.LENGTH_SHORT).show()
}

fun getColor(context: Context, color: Int) = ContextCompat.getColor(context, color)
