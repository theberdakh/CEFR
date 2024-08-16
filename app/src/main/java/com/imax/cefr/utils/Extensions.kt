package com.imax.cefr.utils

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import com.imax.cefr.BuildConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

fun View.gone() {
    this.visibility = View.GONE
}

fun EditText.getString() = this.text.toString()

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun <V> Fragment.collectFlowLatest(
    collectableFlow: Flow<V>,
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    actionOnCollect: suspend (value: V) -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        repeatOnLifecycle(lifecycleState) {
            collectableFlow.collectLatest { value ->
                actionOnCollect(value)
            }
        }
    }
}
fun Fragment.toastMessage(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Fragment.snackBar(message: String?) {
    Snackbar.make(requireView(), message ?: "", Snackbar.LENGTH_SHORT).show()
}

fun getColor(context: Context, color: Int) = ContextCompat.getColor(context, color)


fun View.showBorder(color: Int) {
    val STROKE_WIDTH_DEFAULT = 2
    this.showBorder(2, color)
}

fun View.showBorder(width: Int, color: Int) {
    val background: GradientDrawable = when (this.background) {
        null -> {
            GradientDrawable()
        }
        is GradientDrawable -> {
            this.background as GradientDrawable
        }
        else -> {
            val msg = "setBackgroundBorder() called on View that has background other than GradientDrawable"
            if (BuildConfig.DEBUG) {
                throw RuntimeException(msg)
            } else {
                //Log here
            }
            return
        }
    }
    background.setStroke(width, color)
    this.background = background
}


