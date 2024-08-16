package com.imax.cefr.fragments.teacher.stream.schedule

import android.annotation.SuppressLint
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.imax.cefr.R
import com.imax.cefr.core.base.fragment.BaseFragment
import com.imax.cefr.core.base.resource.Resource
import com.imax.cefr.data.models.stream.CreateStreamRequestData
import com.imax.cefr.databinding.FragmentStreamScheduleBinding
import com.imax.cefr.fragments.teacher.adapter.CourseNumber
import com.imax.cefr.fragments.teacher.adapter.CourseNumberListAdapter
import com.imax.cefr.presentation.LoginViewModel
import com.imax.cefr.presentation.StreamViewModel
import com.imax.cefr.utils.addMinutes
import com.imax.cefr.utils.getString
import com.imax.cefr.utils.showBorder
import com.imax.cefr.utils.toastMessage
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.math.min

class ScheduleStreamFragment :
    BaseFragment<FragmentStreamScheduleBinding>(FragmentStreamScheduleBinding::inflate) {
    private val streamViewModel by viewModel<StreamViewModel>()


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
        tvPreviewFullName.text = localStorage.getUser().name

        etAddTheme.doAfterTextChanged { text ->
            if (text.toString().isEmpty()) {
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
                val time = String.format("%02d:%02d", hour, minute)
                etAddTime.setText(time)
                tvPreviewTime.text = time
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
                    description = localStorage.getUser().channelName,
                    endTime = endTime,
                    group = tvCourseNumber.text.toString(),
                    status = "planned",
                    teacherId = localStorage.getUser().id,
                    teacherName = localStorage.getUser().name
                )
            )
        }

    }



    override fun FragmentStreamScheduleBinding.observeViewModel() {
        adapter.submitList(fakeCourseNumbers)

        streamViewModel.createStreamFlow.onEach { result ->
            when(result) {
                is Resource.Success  -> {
                    toastMessage(result.value.message)
                    requireActivity().supportFragmentManager.popBackStack()
                }
                is Resource.Error -> {
                    result.data?.message?.let {
                        toastMessage(it)
                    }

                }
                is Resource.Loading -> {}
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }


}
