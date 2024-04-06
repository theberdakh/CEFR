package com.example.cefr.utils

import android.content.Context
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cefr.view.MainActivity
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

const val TAG = "TTTT"


fun formatPrice(number: Int) = number.toString().replace(Regex("(\\d)(?=(\\d{3})+\$)"), "$1 ")

fun Fragment.snackBar(message: String?) {
    Snackbar.make(requireView(), message ?: "", Snackbar.LENGTH_SHORT).show()
}

fun Fragment.toastMessage(message: String) {
    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun Fragment.showKeyboard(editText: EditText) {
    val keyboard =
        (this.requireActivity() as MainActivity).getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    keyboard.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT)
}

fun Fragment.hideKeyboard(view: View) {
    val keyboard =
        (this.requireActivity() as MainActivity).getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    keyboard.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Int.milliSecondsToTimer(): String {
    val pattern = if (this >= 3_600_000L) "HH:mm:ss" else "mm:ss"
    val simpleDataFormat = SimpleDateFormat(pattern, Locale.ROOT)
    simpleDataFormat.timeZone = TimeZone.getTimeZone("GMT+0")
    return simpleDataFormat.format(this)
}

fun ImageView.setImage(name: String) {
    this.setImageURI(Uri.parse("android.resource://com.example.erteklerqosqlarhmtaqmaqlar/drawable/$name"))
}

infix fun Int.equally(duration: Int): Boolean {

    val a = duration / 1000
    val b = this / 1000

    return a == b

}


fun saveUserToken(context: Context, token: String) {
    val pref = context.getSharedPreferences("my_pref", Context.MODE_PRIVATE)
    pref.edit().putString("user_token", token).apply()
}

fun getUserToken(context: Context): String {
    val pref = context.getSharedPreferences("my_pref", Context.MODE_PRIVATE)
    return pref.getString("user_token", "null") ?: "null"
}