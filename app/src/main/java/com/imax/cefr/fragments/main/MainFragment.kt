package com.imax.cefr.fragments.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.imax.cefr.R
import com.imax.cefr.fragments.teacher.adapter.LiveVideDataClassListAdapter
import com.imax.cefr.data.models.LiveVideoDataClass
import com.imax.cefr.databinding.FragmentMainBinding
import com.imax.cefr.data.pref.LocalStorage
import com.imax.cefr.MainActivity
import com.imax.cefr.fragments.teacher.adapter.PinnedLiveStreamsAdapter
import org.koin.android.ext.android.inject

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var vpAdapter: PinnedLiveStreamsAdapter
    private lateinit var rvAdapter: LiveVideDataClassListAdapter
    private val localStorage: LocalStorage by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)
        initVariables()
    }

    private fun initVariables() {
        mainActivity = requireActivity() as MainActivity
        mainActivity.settingsBottomNavigationStudent(false)
        mainActivity.settingsBottomNavigation(true)
        vpAdapter = PinnedLiveStreamsAdapter()
        rvAdapter = LiveVideDataClassListAdapter()

        binding.viewPager.adapter = vpAdapter
        val list = mutableListOf<LiveVideoDataClass>()
        repeat(10) {
            list.add(
                LiveVideoDataClass(
                    it
                )
            )
        }
        vpAdapter.submitList(list)

        val list2 = mutableListOf<LiveVideoDataClass>()
        repeat(5) {
            list2.add(LiveVideoDataClass(it))
        }
        rvAdapter.submitList(list2)

        if (localStorage.channelName == "amir_b1") {
            binding.cardInnerOne.visibility = View.GONE
        } else if (localStorage.channelName == "user_nukus") {
            binding.cardInnerTwo.visibility = View.GONE
        } else if (localStorage.channelName == "user_xojeli") {
            binding.cardInnerThree.visibility = View.GONE
        } else if (localStorage.channelName == "user_shomanay") {
            binding.cardInnerFour.visibility = View.GONE
        } else if (localStorage.channelName == "user_kegeyli") {
            binding.cardInnerFive.visibility = View.GONE
        }

        binding.cardInnerOne.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToWebViewFragment("amir_b1")
            )
        }

        binding.cardInnerTwo.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToWebViewFragment("user_nukus")
            )
        }

        binding.cardInnerThree.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToWebViewFragment("user_xojeli")
            )
        }

        binding.cardInnerFour.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToWebViewFragment("user_shomanay")
            )
        }

        binding.cardInnerFive.setOnClickListener {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToWebViewFragment("user_kegeyli")
            )
        }
    }
}
