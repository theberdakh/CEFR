package com.imax.cefr.fragments.channel

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.imax.cefr.core.base.fragment.BaseFragment
import com.imax.cefr.core.base.resource.Resource
import com.imax.cefr.databinding.FragmentChannelBinding
import com.imax.cefr.presentation.TwitchViewModel
import com.imax.cefr.utils.toastMessage
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChannelFragment(private val channelUsername: String) :
    BaseFragment<FragmentChannelBinding>(FragmentChannelBinding::inflate) {
    private val twitchViewModel by viewModel<TwitchViewModel>()
    private val adapter by lazy(LazyThreadSafetyMode.NONE) { TwitchVideosAdapter() }

    override fun FragmentChannelBinding.navigation() {
    }

    override fun FragmentChannelBinding.setUpViews() {
        channelBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        channelRecyclerView.adapter = adapter
    }

    override fun FragmentChannelBinding.observeViewModel() {

        name.text = channelUsername

        twitchViewModel.loginUser(channelUsername)

        twitchViewModel.loginFlow.onEach {
            when(it){
                is Resource.Success -> {
                    twitchViewModel.getAllStreamsByUserId("1103815460")
                    toastMessage( it.value.data[0].toString())
                }
                is Resource.Error -> {
                    it.error?.printStackTrace()
                    subtitle.text = it.error.toString()
                    toastMessage(it.error.toString())
                }
                is Resource.Loading -> {}
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        twitchViewModel.userVideosFlow.onEach {
            when(it){
                is Resource.Success -> {
                    toastMessage(it.value.data.size.toString())
                    adapter.submitList(it.value.data)
                }
                is Resource.Error -> {
                    it.error?.printStackTrace()
                    toastMessage(it.error.toString())
                }
                is Resource.Loading -> {}
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }


}
