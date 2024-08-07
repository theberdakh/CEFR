package com.imax.cefr.fragments.teacher.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.imax.cefr.R
import com.imax.cefr.fragments.teacher.adapter.LiveVideDataClassListAdapter
import com.imax.cefr.data.models.LiveVideoDataClass
import com.imax.cefr.databinding.FragmentProfileBinding
import com.imax.cefr.data.pref.LocalStorage
import com.imax.cefr.MainActivity
import com.imax.cefr.fragments.teacher.adapter.PinnedLiveStreamsAdapter
import org.koin.android.ext.android.inject

class TeacherProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var vpAdapter: PinnedLiveStreamsAdapter
    private lateinit var rvAdapter: LiveVideDataClassListAdapter
    private val localStorage: LocalStorage by inject()
    private lateinit var mainActivity: MainActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProfileBinding.bind(view)

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

        binding.tvOne.text = localStorage.fullName
        binding.rcPrevious.adapter = rvAdapter
        val list2 = mutableListOf<LiveVideoDataClass>()
        repeat(2) {
            list2.add(LiveVideoDataClass(it))
        }
        rvAdapter.submitList(list2)

    }
}
