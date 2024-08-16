package com.imax.cefr.fragments.teacher.home.all

import androidx.lifecycle.lifecycleScope
import androidx.paging.filter
import androidx.paging.map
import com.imax.cefr.core.base.fragment.BaseFragment
import com.imax.cefr.core.base.resource.Resource
import com.imax.cefr.data.models.stream.LiveStreamStatus
import com.imax.cefr.databinding.FragmentListStreamsBinding
import com.imax.cefr.fragments.teacher.home.AllStreamsPagingAdapter
import com.imax.cefr.fragments.teacher.home.StreamRecyclerAdapter
import com.imax.cefr.presentation.StreamViewModel
import com.imax.cefr.utils.collectFlowLatest
import com.imax.cefr.utils.toastMessage
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllEndedStreamsFragment: BaseFragment<FragmentListStreamsBinding>(FragmentListStreamsBinding::inflate) {
    private val streamViewModel by viewModel<StreamViewModel>()
    private val adapter by lazy { AllStreamsPagingAdapter() }


    override fun FragmentListStreamsBinding.navigation() {}

    override fun FragmentListStreamsBinding.setUpViews() {
        listStreamsRecyclerView.adapter = adapter
        streamViewModel.getAllStream(20)
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
