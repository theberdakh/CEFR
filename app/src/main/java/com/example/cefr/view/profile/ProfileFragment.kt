package com.example.cefr.view.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.cefr.R
import com.example.cefr.RVAdapter
import com.example.cefr.data.models.LiveVideoDataClass
import com.example.cefr.databinding.FragmentProfileBinding
import com.example.cefr.view.ViewPagerAdapter

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var vpAdapter: ViewPagerAdapter
    private lateinit var rvAdapter: RVAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProfileBinding.bind(view)

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
        val list2 = mutableListOf<LiveVideoDataClass>()
        repeat(2) {
            list2.add(LiveVideoDataClass(it))
        }
        rvAdapter.submitList(list2)

    }
}