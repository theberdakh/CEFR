package com.imax.cefr.fragments.teacher.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.imax.cefr.R
import com.imax.cefr.fragments.teacher.adapter.LiveVideDataClassListAdapter
import com.imax.cefr.data.models.LiveVideoDataClass
import com.imax.cefr.databinding.FragmentProfileBinding
import com.imax.cefr.core.base.pref.LocalStorage
import com.imax.cefr.MainActivity
import com.imax.cefr.core.base.fragment.BaseFragment
import com.imax.cefr.fragments.teacher.adapter.PinnedLiveStreamsAdapter
import org.koin.android.ext.android.inject

class TeacherProfileFragment :BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate){

    private val vpAdapter by lazy(LazyThreadSafetyMode.NONE) {PinnedLiveStreamsAdapter() }
    private val rvAdapter by lazy(LazyThreadSafetyMode.NONE) { LiveVideDataClassListAdapter() }
    private val localStorage: LocalStorage by inject()
    private lateinit var mainActivity: MainActivity
    private val list = mutableListOf<LiveVideoDataClass>()
    private val list2 = mutableListOf<LiveVideoDataClass>()

    override fun FragmentProfileBinding.observeViewModel() {
        //observe view model here
    }

    override fun FragmentProfileBinding.setUpViews() {
        mainActivity = requireActivity() as MainActivity
        mainActivity.settingsBottomNavigationStudent(false)
        mainActivity.settingsBottomNavigation(true)

       viewPager.adapter = vpAdapter

        repeat(10) {
            list.add(
                LiveVideoDataClass(
                    it
                )
            )
        }
        vpAdapter.submitList(list)

        tvOne.text = localStorage.fullName
        rcPrevious.adapter = rvAdapter

        repeat(2) {
            list2.add(LiveVideoDataClass(it))
        }
        rvAdapter.submitList(list2)

    }

    override fun FragmentProfileBinding.navigation() {}
}
