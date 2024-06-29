package com.example.cefr.view.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.cefr.R
import com.example.cefr.RVAdapter
import com.example.cefr.data.models.LiveVideoDataClass
import com.example.cefr.databinding.FragmentProfileBinding
import com.example.cefr.utils.LocalStorage
import com.example.cefr.view.MainActivity
import com.example.cefr.view.ViewPagerAdapter
import org.koin.android.ext.android.inject

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var vpAdapter: ViewPagerAdapter
    private lateinit var rvAdapter: RVAdapter
    private val localStorage: LocalStorage by inject()
    private lateinit var mainActivity: MainActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProfileBinding.bind(view)

        mainActivity = requireActivity() as MainActivity
        mainActivity.settingsBottomNavigationStudent(false)
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

        binding.tvOne.text = localStorage.fullName
        binding.rcPrevious.adapter = rvAdapter
        val list2 = mutableListOf<LiveVideoDataClass>()
        repeat(2) {
            list2.add(LiveVideoDataClass(it))
        }
        rvAdapter.submitList(list2)

    }
}