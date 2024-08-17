package com.imax.cefr.fragments.home.all

import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.paging.filter
import com.imax.cefr.R
import com.imax.cefr.core.base.fragment.BaseFragment
import com.imax.cefr.core.base.fragment.addFragmentToBackStack
import com.imax.cefr.core.base.resource.Resource
import com.imax.cefr.data.models.stream.LiveStreamStatus
import com.imax.cefr.data.models.twitch.live.TwitchLiveDataChild
import com.imax.cefr.databinding.FragmentListStreamsBinding
import com.imax.cefr.fragments.home.adapter.AllStreamsPagingAdapter
import com.imax.cefr.fragments.home.adapter.TwitchLiveVideosAdapter
import com.imax.cefr.fragments.teacher.stream.watch.WatchStreamFragment
import com.imax.cefr.presentation.StreamViewModel
import com.imax.cefr.presentation.TwitchViewModel
import com.imax.cefr.utils.collectFlowLatest
import com.imax.cefr.utils.toastMessage
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel


class AllLiveStreamsFragment :
    BaseFragment<FragmentListStreamsBinding>(FragmentListStreamsBinding::inflate) {
    private val streamViewModel by viewModel<StreamViewModel>()
    private val twitchViewModel by viewModel<TwitchViewModel>()
    private val twitchAdapter by lazy { TwitchLiveVideosAdapter() }
    private val adapter by lazy { AllStreamsPagingAdapter() }


    override fun FragmentListStreamsBinding.navigation() {}

    override fun FragmentListStreamsBinding.setUpViews() {

        listStreamsRecyclerView.adapter = twitchAdapter

      /*  listStreamsRefresh.setOnRefreshListener {
            adapter.submitData(lifecycle, PagingData.empty())
            streamViewModel.getAllStream(20)
            listStreamsRefresh.isRefreshing = false
        }

        listStreamsRecyclerView.adapter = adapter


        streamViewModel.getAllStream(20)
        adapter.setOnClickSteamListener { stream ->
            requireActivity().supportFragmentManager.
            addFragmentToBackStack(R.id.activity_container_view, WatchStreamFragment(stream))
        }*/
    }

    override fun FragmentListStreamsBinding.observeViewModel() {

        twitchViewModel.getLive(listOf("1103815460", "509012821", "1103829928", "1103831671", "1103834869"))

        twitchViewModel.liveUserFlow.onEach { response ->
            when(response){
                is Resource.Success -> {
                    val mutableList = mutableListOf<TwitchLiveDataChild>()
                    for (i in response.value){
                        mutableList.addAll(i.data)
                    }
                    twitchAdapter.submitList(mutableList)
                }
                is Resource.Error -> toastMessage("Error")
                Resource.Loading -> {}

            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
      /*  Log.d("Token", localStorage.getToken())
        streamViewModel.allStreamFlow.onEach { allStreamsResponseResource ->
            collectFlowLatest(allStreamsResponseResource) {pagingData ->
                val filter = pagingData.filter { it.status == LiveStreamStatus.LIVE.status }
                adapter.submitData(filter)
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)*/

    }


}
