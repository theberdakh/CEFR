package com.imax.cefr.fragments.home.all

import com.imax.cefr.R
import com.imax.cefr.core.base.fragment.BaseFragment
import com.imax.cefr.core.base.fragment.addFragmentToBackStack
import com.imax.cefr.data.models.stream.StreamResponseData
import com.imax.cefr.databinding.FragmentListStreamsBinding
import com.imax.cefr.fragments.channel.ChannelFragment
import com.imax.cefr.fragments.home.adapter.ChannelsListAdapter

class AllChannelsFragment :
    BaseFragment<FragmentListStreamsBinding>(FragmentListStreamsBinding::inflate) {
    private val adapter by lazy { ChannelsListAdapter() }
    override fun FragmentListStreamsBinding.navigation() {}

    override fun FragmentListStreamsBinding.setUpViews() {
        listStreamsRecyclerView.adapter = adapter
        adapter.setOnClickChannelListener { stream ->
            requireActivity().supportFragmentManager.addFragmentToBackStack(
            R.id.activity_container_view,
            ChannelFragment(stream)
        ) }
    }

    override fun FragmentListStreamsBinding.observeViewModel() {
        val channels = listOf(
            StreamResponseData(
                id = "id",
                streamTitle = "Title",
                description = "amir_b1",
                startTime = "",
                endTime = "",
                group = "",
                teacherId = "+1234567890",
                teacherName = "Amir",
                status = ""
            ),
            StreamResponseData(
                id = "id",
                streamTitle = "Title",
                description = "user_nukus",
                startTime = "",
                endTime = "",
                group = "",
                teacherId = "+0987654321",
                teacherName = "Ruslan Joldasbaev",
                status = ""
            ),
            StreamResponseData(
                id = "id",
                streamTitle = "Title",
                description = "user_xojeli",
                startTime = "",
                endTime = "",
                group = "",
                teacherId = "+1122334455",
                teacherName = "Asadbek Qogambaev",
                status = ""
            ),
            StreamResponseData(
                id = "id",
                streamTitle = "Title",
                description = "user_shomanay",
                startTime = "",
                endTime = "",
                group = "",
                teacherId = "+5566778899",
                teacherName = "Nawrizbay Baltabaev",
                status = ""
            ),
            StreamResponseData(
                id = "id",
                streamTitle = "Title",
                description = "user_kegeyli",
                startTime = "",
                endTime = "",
                group = "",
                teacherId = "+6677889900",
                teacherName = "Berdax",
                status = ""
            ),

            )

        adapter.submitList(channels)
    }

}
