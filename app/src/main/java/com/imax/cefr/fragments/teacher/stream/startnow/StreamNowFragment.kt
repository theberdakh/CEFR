package com.imax.cefr.fragments.teacher.stream.startnow

import androidx.core.widget.doAfterTextChanged
import com.imax.cefr.R
import com.imax.cefr.core.base.fragment.BaseFragment
import com.imax.cefr.databinding.FragmentStreamScheduleBinding
import com.imax.cefr.fragments.teacher.adapter.CourseNumber
import com.imax.cefr.fragments.teacher.adapter.CourseNumberListAdapter
import com.imax.cefr.utils.gone
import java.util.Calendar
import java.util.TimeZone

class StreamNowFragment :
    BaseFragment<FragmentStreamScheduleBinding>(FragmentStreamScheduleBinding::inflate) {

    private val adapter by lazy(LazyThreadSafetyMode.NONE) { CourseNumberListAdapter() }
    private val calendar by lazy(LazyThreadSafetyMode.NONE) { Calendar.getInstance(TimeZone.getDefault()) }

    private val fakeCourseNumbers = listOf(
        CourseNumber(103),
        CourseNumber(211),
        CourseNumber(122),
        CourseNumber(123),
        CourseNumber(153)
    )


    override fun FragmentStreamScheduleBinding.navigation() {
        toolbarSchedule.setNavigationOnClickListener {
            goBack()
        }
    }

    override fun FragmentStreamScheduleBinding.setUpViews() {
        rvCourseNumbers.adapter = adapter

        toolbarSchedule.title = getString(R.string.title_start_now)
        btnSchedule.setText(R.string.title_start_streaming)
        tvPreviewFullName.text = localStorage.fullName



        tvPreviewDate.text = getCurrentDate()
        tvPreviewTime.text = getCurrentTime()

        etAddDate.gone()
        etAddTime.gone()

        etAddTheme.doAfterTextChanged { text ->
            if (text.toString().isEmpty()) {
                tvPreviewTheme.setText(R.string.hint_theme)
            } else {
                tvPreviewTheme.text = text
            }
        }

        adapter.setOnClickListener { courseNumber ->
            tvCourseNumber.text = courseNumber.toString()
        }
    }

    private fun getCurrentTime(): String {
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        return String.format("%02d:%02d", hour, minute)
    }

    private fun getCurrentDate(): String {

        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        return String.format("%02d/%02d/%04d", day, month, year)
    }


    override fun FragmentStreamScheduleBinding.observeViewModel() {
        adapter.submitList(fakeCourseNumbers)
    }

}
