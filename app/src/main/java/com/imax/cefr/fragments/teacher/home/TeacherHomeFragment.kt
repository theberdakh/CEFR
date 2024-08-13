package com.imax.cefr.fragments.teacher.home

import android.view.View
import com.imax.cefr.R
import com.imax.cefr.core.base.fragment.BaseFragment
import com.imax.cefr.core.base.fragment.addFragmentToBackStack
import com.imax.cefr.core.base.pref.LocalStorage
import com.imax.cefr.data.models.LiveVideoDataClass
import com.imax.cefr.databinding.FragmentTeacherHomeBinding
import com.imax.cefr.fragments.teacher.adapter.LiveVideDataClassListAdapter
import com.imax.cefr.fragments.teacher.adapter.PinnedLiveStreamsAdapter
import com.imax.cefr.fragments.teacher.watch.WatchStreamFragment
import org.koin.android.ext.android.inject

class TeacherHomeFragment: BaseFragment<FragmentTeacherHomeBinding>(FragmentTeacherHomeBinding::inflate) {
    private val vpAdapter by lazy(LazyThreadSafetyMode.NONE) { PinnedLiveStreamsAdapter() }
    private val rvAdapter by lazy(LazyThreadSafetyMode.NONE) { LiveVideDataClassListAdapter()  }


    override fun FragmentTeacherHomeBinding.navigation() {}

    override fun FragmentTeacherHomeBinding.setUpViews() {
        viewPager.adapter = vpAdapter
        val list = mutableListOf<LiveVideoDataClass>()
        repeat(10) { list.add(LiveVideoDataClass(it)) }
        vpAdapter.submitList(list.toList())

        val list2 = mutableListOf<LiveVideoDataClass>()
        repeat(5) { list2.add(LiveVideoDataClass(it)) }
        rvAdapter.submitList(list2.toList())

        when (localStorage.getUser().channelName) {
            "amir_b1" -> {
                cardInnerOne.visibility = View.GONE
            }
            "user_nukus" -> {
                cardInnerTwo.visibility = View.GONE
            }
            "user_xojeli" -> {
                cardInnerThree.visibility = View.GONE
            }
            "user_shomanay" -> {
                cardInnerFour.visibility = View.GONE
            }
            "user_kegeyli" -> {
                cardInnerFive.visibility = View.GONE
            }
        }

        cardInnerOne.setOnClickListener {
            parentFragmentManager.addFragmentToBackStack(R.id.activity_container_view, WatchStreamFragment("amir_b1"))
          //  MainFragmentDirections.actionMainFragmentToWebViewFragment("amir_b1")
        }

        cardInnerTwo.setOnClickListener {
            requireActivity().supportFragmentManager.addFragmentToBackStack(R.id.activity_container_view, WatchStreamFragment("user_nukus"))
        }

        /*
        cardInnerThree.setOnClickListener {
            MainFragmentDirections.actionMainFragmentToWebViewFragment("user_xojeli")
        }

        cardInnerFour.setOnClickListener {
            MainFragmentDirections.actionMainFragmentToWebViewFragment("user_shomanay")
        }

        binding.cardInnerFive.setOnClickListener {
            MainFragmentDirections.actionMainFragmentToWebViewFragment("user_kegeyli")
        }*/

    }

    override fun FragmentTeacherHomeBinding.observeViewModel() {}

}
