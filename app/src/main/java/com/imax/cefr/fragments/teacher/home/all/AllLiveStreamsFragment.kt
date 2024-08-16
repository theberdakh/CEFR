package com.imax.cefr.fragments.teacher.home.all

import androidx.lifecycle.lifecycleScope
import com.imax.cefr.core.base.fragment.BaseFragment
import com.imax.cefr.core.base.resource.Resource
import com.imax.cefr.data.models.stream.LiveStreamStatus
import com.imax.cefr.databinding.FragmentListStreamsBinding
import com.imax.cefr.fragments.teacher.home.StreamRecyclerAdapter
import com.imax.cefr.presentation.StreamViewModel
import com.imax.cefr.utils.toastMessage
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel


class AllLiveStreamsFragment :
    BaseFragment<FragmentListStreamsBinding>(FragmentListStreamsBinding::inflate) {
    private val streamViewModel by viewModel<StreamViewModel>()
    private val adapter by lazy { StreamRecyclerAdapter() }


    override fun FragmentListStreamsBinding.navigation() {}

    override fun FragmentListStreamsBinding.setUpViews() {
        listStreamsRecyclerView.adapter = adapter
        streamViewModel.getAllStream()
    }

    override fun FragmentListStreamsBinding.observeViewModel() {

        streamViewModel.allStreamFlow.onEach { allStreamsResponseResource ->
            when (allStreamsResponseResource) {
                is Resource.Success -> {
                    allStreamsResponseResource.data?.streams?.let { streams ->
                        val liveStreams = streams.filter { it.status == LiveStreamStatus.LIVE.status }
                        adapter.submitList(liveStreams)
                    }

                }

                is Resource.Error -> {
                    allStreamsResponseResource.throwable.printStackTrace()
                    toastMessage(allStreamsResponseResource.throwable.toString())
                }

                is Resource.Loading -> {}
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

    }


}
