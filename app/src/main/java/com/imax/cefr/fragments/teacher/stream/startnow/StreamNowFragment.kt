package com.imax.cefr.fragments.teacher.stream.startnow

import android.content.Intent
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.imax.cefr.R
import com.imax.cefr.core.base.fragment.BaseFragment
import com.imax.cefr.core.base.resource.Resource
import com.imax.cefr.data.models.stream.CreateStreamRequestData
import com.imax.cefr.databinding.FragmentStreamScheduleBinding
import com.imax.cefr.fragments.teacher.adapter.CourseNumber
import com.imax.cefr.fragments.teacher.adapter.CourseNumberListAdapter
import com.imax.cefr.fragments.teacher.stream.live.LiveStreamActivity
import com.imax.cefr.presentation.StreamViewModel
import com.imax.cefr.utils.addMinutes
import com.imax.cefr.utils.getString
import com.imax.cefr.utils.gone
import com.imax.cefr.utils.toastMessage
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Calendar
import java.util.TimeZone

class StreamNowFragment :
    BaseFragment<FragmentStreamScheduleBinding>(FragmentStreamScheduleBinding::inflate) {

    private val adapter by lazy(LazyThreadSafetyMode.NONE) { CourseNumberListAdapter() }
    private val calendar by lazy(LazyThreadSafetyMode.NONE) { Calendar.getInstance(TimeZone.getDefault()) }
    private val streamViewModel by viewModel<StreamViewModel>()

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
        tvPreviewFullName.text = localStorage.getUser().name


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

        btnSchedule.setOnClickListener {
            val startTime = "${tvPreviewDate.text}T${tvPreviewTime.text}:00.050Z"
            val endTimeAfter80Minutes = addMinutes(tvPreviewTime.text, 80L)
            val endTime = "${tvPreviewDate.text}T$endTimeAfter80Minutes:00.050Z"

            streamViewModel.createStream(
                CreateStreamRequestData(
                streamTitle = etAddTheme.getString(),
                startTime = startTime,
                description = "dscdd",
                endTime = endTime,
                group = tvCourseNumber.text.toString(),
                status = "live",
                teacherId = localStorage.getUser().id,
                teacherName = localStorage.getUser().name
            )
            )
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

        return String.format("%04d-%02d-%02d", year, month, day)
    }


    override fun FragmentStreamScheduleBinding.observeViewModel() {
        adapter.submitList(fakeCourseNumbers)

        streamViewModel.createStreamFlow.onEach { result ->
            when(result){
                is Resource.Success -> {
                    toastMessage(result.value.message)
                    val intent = Intent(requireContext(), LiveStreamActivity::class.java)
                    startActivity(intent)
                }
                is Resource.Error -> {
                    toastMessage("Error")
                }
                is Resource.Loading -> {}
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

}
