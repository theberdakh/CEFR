package com.imax.cefr.fragments.home

import com.google.android.material.tabs.TabLayoutMediator
import com.imax.cefr.R
import com.imax.cefr.core.base.fragment.BaseFragment
import com.imax.cefr.databinding.FragmentTeacherHomeBinding

class TeacherHomeFragment: BaseFragment<FragmentTeacherHomeBinding>(FragmentTeacherHomeBinding::inflate) {
    private val vpAdapter by lazy(LazyThreadSafetyMode.NONE)
    { TeacherHomeViewPagerAdapter(requireActivity().supportFragmentManager, requireActivity().lifecycle) }

    override fun FragmentTeacherHomeBinding.navigation() {}

    override fun FragmentTeacherHomeBinding.setUpViews() {

        viewPager.adapter = vpAdapter
        TabLayoutMediator(teacherHomeTabLayout, viewPager){ tab, position ->
            when(position){
                0 -> {
                    tab.text = "Live"
                    tab.setIcon(R.drawable.stream_start)
                }
                1 -> {
                    tab.text = "Planned"
                }
                2 -> tab.text = "All channels"
            }
        }.attach()

    }

    override fun FragmentTeacherHomeBinding.observeViewModel() {}

}
