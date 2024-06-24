package com.example.cefr.view.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.cefr.R
import com.example.cefr.RVAdapter
import com.example.cefr.data.models.LiveVideoDataClass
import com.example.cefr.databinding.FragmentMainBinding
import com.example.cefr.view.MainActivity
import com.example.cefr.view.ViewPagerAdapter

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var vpAdapter: ViewPagerAdapter
    private lateinit var rvAdapter: RVAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)
        initVariables()

    }

    private fun initVariables() {
        mainActivity = requireActivity() as MainActivity
        mainActivity.settingsBottomNavigation(true)
        vpAdapter = ViewPagerAdapter()
        rvAdapter = RVAdapter()

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

        binding.rcPrevious.adapter = rvAdapter
        binding.rcTomorrow.adapter = rvAdapter
        val list2 = mutableListOf<LiveVideoDataClass>()
        repeat(2) {
            list2.add(LiveVideoDataClass(it))
        }
        rvAdapter.submitList(list2)

    }
}