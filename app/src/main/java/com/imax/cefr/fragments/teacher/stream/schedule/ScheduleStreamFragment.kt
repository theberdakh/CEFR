package com.imax.cefr.fragments.teacher.stream.schedule

import android.util.Log
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.imax.cefr.R
import com.imax.cefr.core.base.fragment.BaseFragment
import com.imax.cefr.core.base.fragment.Hour
import com.imax.cefr.core.base.fragment.Minute
import com.imax.cefr.core.base.pref.LocalStorage
import com.imax.cefr.databinding.FragmentStreamScheduleBinding
import com.imax.cefr.fragments.teacher.adapter.CourseNumber
import com.imax.cefr.fragments.teacher.adapter.CourseNumberListAdapter
import org.koin.android.ext.android.inject
import kotlin.math.min

class ScheduleStreamFragment: BaseFragment<FragmentStreamScheduleBinding>(FragmentStreamScheduleBinding::inflate) {

    private val fakeCourseNumbers = listOf(
        CourseNumber(103),
        CourseNumber(211),
        CourseNumber(122),
        CourseNumber(123),
        CourseNumber(153)
    )

    private val adapter by lazy(LazyThreadSafetyMode.NONE) { CourseNumberListAdapter() }

    override fun FragmentStreamScheduleBinding.navigation() {


        toolbarSchedule.setNavigationOnClickListener {
            goBack()
        }

    }

    override fun FragmentStreamScheduleBinding.setUpViews() {

        btnSchedule.text = getText(R.string.title_schedule)

        rvCourseNumbers.adapter = adapter

        tvPreviewFullName.text = localStorage.fullName

        etAddTheme.doAfterTextChanged { text ->
            if (text.toString().isEmpty()){
                tvPreviewTheme.setText(R.string.hint_theme)
            } else {
                tvPreviewTheme.text = text
            }
        }

        etAddDate.setOnClickListener {
            openDataPickerDialog { text ->
                etAddDate.setText(text)
                tvPreviewDate.text = text
            }
        }

        etAddTime.setOnClickListener {
            openTimePickerDialog { hour, minute ->
                val time = getString(R.string.hour_and_minute, hour, minute)
                etAddTime.setText(time)
                tvPreviewTime.text = time
            }
        }

        adapter.setOnClickListener { courseNumber ->
            tvCourseNumber.text = courseNumber.toString()
        }

    }

    override fun FragmentStreamScheduleBinding.observeViewModel() {
        adapter.submitList(fakeCourseNumbers)
    }


}
