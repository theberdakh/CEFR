package com.imax.cefr.fragments.teacher.profile

import com.imax.cefr.core.base.fragment.BaseFragment
import com.imax.cefr.core.base.pref.LocalStorage
import com.imax.cefr.data.models.LiveVideoDataClass
import com.imax.cefr.databinding.FragmentTeacherProfileBinding
import com.imax.cefr.fragments.teacher.adapter.LiveVideDataClassListAdapter
import com.imax.cefr.fragments.teacher.adapter.PinnedLiveStreamsAdapter
import org.koin.android.ext.android.inject

class TeacherProfileFragment :BaseFragment<FragmentTeacherProfileBinding>(FragmentTeacherProfileBinding::inflate){

    private val vpAdapter by lazy(LazyThreadSafetyMode.NONE) {PinnedLiveStreamsAdapter() }
    private val rvAdapter by lazy(LazyThreadSafetyMode.NONE) { LiveVideDataClassListAdapter() }
    private val list = mutableListOf<LiveVideoDataClass>()
    private val list2 = mutableListOf<LiveVideoDataClass>()

    override fun FragmentTeacherProfileBinding.observeViewModel() {
        //observe view model here
    }

    override fun FragmentTeacherProfileBinding.setUpViews() {


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

    override fun FragmentTeacherProfileBinding.navigation() {}
}
