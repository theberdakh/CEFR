package com.imax.cefr.fragments.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.imax.cefr.fragments.home.all.AllChannelsFragment
import com.imax.cefr.fragments.home.all.AllEndedStreamsFragment
import com.imax.cefr.fragments.home.all.AllLiveStreamsFragment
import com.imax.cefr.fragments.home.all.AllPlannedStreamsFragment

class TeacherHomeViewPagerAdapter(private val fragmentManager: FragmentManager, private val lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount() = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
             0 -> AllLiveStreamsFragment()
             1 -> AllPlannedStreamsFragment()
             else -> AllChannelsFragment()
        }
    }

}
