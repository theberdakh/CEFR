package com.imax.cefr.core.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.imax.cefr.R
import com.imax.cefr.core.base.pref.LocalStorage
import org.koin.android.ext.android.inject
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

typealias Inflate<VB> =(LayoutInflater, ViewGroup?, Boolean) -> VB
typealias Minute = Int
typealias Hour = Int

abstract class BaseFragment<VB: ViewBinding>(private val inflate: Inflate<VB>) : Fragment() {
    var binding: VB? = null
    protected val localStorage: LocalStorage by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            observeViewModel()
            setUpViews()
            navigation()
        }
    }

    abstract fun VB.navigation()

    abstract fun VB.setUpViews()

    abstract fun VB.observeViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = inflate.invoke(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    protected fun showInputEditText(etView: EditText) {
        etView.requestFocus()
        val inputMethod =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputMethod?.showSoftInput(etView, InputMethodManager.SHOW_IMPLICIT)
    }

    protected fun hideInputEditText(etView: EditText) {
        etView.clearFocus()
        val inputMethod =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputMethod?.hideSoftInputFromWindow(etView.windowToken, 0)
    }

    protected fun goBack() {
        requireActivity().supportFragmentManager.popBackStack()
    }


    protected fun openDataPickerDialog(positiveButtonClick: (String) -> Unit){
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select date")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR)
            .build()

        datePicker.show(requireActivity().supportFragmentManager, "tag")

        datePicker.addOnPositiveButtonClickListener { selection ->
            positiveButtonClick.invoke(
                convertTimeToDate(selection)
            )
        }
    }

    protected fun openTimePickerDialog(positiveButtonClick: (Hour, Minute) -> Unit){
        val timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setTitleText("Select time")
            .setInputMode(MaterialTimePicker.INPUT_MODE_KEYBOARD)
            .build()

        timePicker.show(requireActivity().supportFragmentManager, "tag")

        timePicker.addOnPositiveButtonClickListener {

            positiveButtonClick.invoke(timePicker.hour, timePicker.minute)
        }

    }

    private fun convertTimeToDate(time: Long): String {
        val utc = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        utc.timeInMillis = time
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return format.format(utc.time)
    }

}
