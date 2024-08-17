package com.imax.cefr.fragments.home.all

import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.paging.filter
import com.imax.cefr.R
import com.imax.cefr.core.base.fragment.BaseFragment
import com.imax.cefr.core.base.fragment.addFragmentToBackStack
import com.imax.cefr.data.models.stream.LiveStreamStatus
import com.imax.cefr.databinding.FragmentListStreamsBinding
import com.imax.cefr.fragments.home.adapter.AllStreamsPagingAdapter
import com.imax.cefr.fragments.teacher.stream.watch.WatchStreamFragment
import com.imax.cefr.presentation.StreamViewModel
import com.imax.cefr.utils.collectFlowLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllEndedStreamsFragment: BaseFragment<FragmentListStreamsBinding>(FragmentListStreamsBinding::inflate) {
    private val streamViewModel by viewModel<StreamViewModel>()
    private val adapter by lazy { AllStreamsPagingAdapter() }

    companion object {
        const val ARG_TEACHER_ID = "teacherId"
        const val ARG_TEACHER_NAME = "teacherName"
    }


    override fun FragmentListStreamsBinding.navigation() {}

    override fun FragmentListStreamsBinding.setUpViews() {


        listStreamsRecyclerView.adapter = adapter
        streamViewModel.getAllStream(20)

        listStreamsRefresh.setOnRefreshListener {
            adapter.submitData(lifecycle, PagingData.empty())
            streamViewModel.getAllStream(20)
            listStreamsRefresh.isRefreshing = false
        }

        adapter.setOnClickSteamListener { stream ->
            requireActivity().supportFragmentManager.
            addFragmentToBackStack(R.id.activity_container_view, WatchStreamFragment(stream).apply {
                arguments = Bundle().apply {
                    putString(ARG_TEACHER_ID, stream.teacherId)
                    putString(ARG_TEACHER_NAME, stream.teacherName)
                }
            })
        }
    }

    override fun FragmentListStreamsBinding.observeViewModel() {
        streamViewModel.allStreamFlow.onEach { allStreamsResponseResource ->
            collectFlowLatest(allStreamsResponseResource) {pagingData ->
                val filter = pagingData.filter { it.status == LiveStreamStatus.ENDED.status }
                adapter.submitData(filter)
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

}
