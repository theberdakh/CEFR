package com.imax.cefr.fragments.channel

import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.imax.cefr.R
import com.imax.cefr.core.base.fragment.BaseFragment
import com.imax.cefr.core.base.fragment.addFragmentToBackStack
import com.imax.cefr.core.base.resource.Resource
import com.imax.cefr.data.models.stream.StreamResponseData
import com.imax.cefr.databinding.FragmentChannelBinding
import com.imax.cefr.fragments.teacher.stream.watch.WatchStreamFragment
import com.imax.cefr.presentation.TwitchViewModel
import com.imax.cefr.utils.toastMessage
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChannelFragment(private val stream: StreamResponseData) :
    BaseFragment<FragmentChannelBinding>(FragmentChannelBinding::inflate) {
    private val twitchViewModel by viewModel<TwitchViewModel>()
    private val adapter by lazy(LazyThreadSafetyMode.NONE) { TwitchVideosAdapter() }

    override fun FragmentChannelBinding.navigation() {}

    override fun FragmentChannelBinding.setUpViews() {

        channelTitle.text = stream.teacherName
        channelSubtitle.text = getString(R.string.teacher)

        adapter.setOnVideoClickListener { twitchStream ->
            requireActivity().supportFragmentManager.addFragmentToBackStack(
                R.id.activity_container_view,
                WatchStreamFragment(stream.copy(description = twitchStream.url)), twitchStream.url
            )

        }

        channelBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        channelRecyclerView.adapter = adapter
    }

    override fun FragmentChannelBinding.observeViewModel() {

        twitchViewModel.loginUser(stream.description)

        twitchViewModel.loginFlow.onEach {
            when (it) {
                is Resource.Success -> {
                    if (it.value.data.isNotEmpty()){
                        twitchViewModel.getAllStreamsByUserId(it.value.data[0].id)
                        val user = it.value.data[0]
                        Glide.with(requireActivity()).
                        load(user.profile_image_url)
                            .centerCrop()
                            .placeholder(R.drawable.ic_profile).into(channelAvatarPic)
                    }
                   }

                is Resource.Error -> {
                    it.error?.printStackTrace()
                    toastMessage(it.error.toString())
                }

                is Resource.Loading -> {}
            }
        }.launchIn(viewLifecycleOwner.lifecycleScope)

        twitchViewModel.userVideosFlow.onEach {
            when (it) {
                is Resource.Success -> {
                    if (it.value.data.isNotEmpty()) {
                        adapter.submitList(it.value.data)
                    }

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
