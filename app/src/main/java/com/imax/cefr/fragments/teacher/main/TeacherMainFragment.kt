package com.imax.cefr.fragments.teacher.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.imax.cefr.R
import com.imax.cefr.core.base.fragment.addFragmentToBackStack
import com.imax.cefr.core.base.fragment.replaceFragment
import com.imax.cefr.databinding.FragmentTeacherMainBinding
import com.imax.cefr.fragments.teacher.home.TeacherHomeFragment
import com.imax.cefr.fragments.teacher.profile.TeacherProfileFragment
import com.imax.cefr.fragments.teacher.stream.schedule.ScheduleStreamFragment
import com.imax.cefr.fragments.teacher.stream.startnow.StreamNowFragment

class TeacherMainFragment : Fragment() {
    private lateinit var binding: FragmentTeacherMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTeacherMainBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.title = "Main"
        childFragmentManager.replaceFragment(
            R.id.fragment_teacher_main_container,
            TeacherHomeFragment()
        )


        binding.fabTeacherMain.setOnClickListener {

            showScheduleOrLiveDialog(
                requireContext(), 
                getString(R.string.title_start_streaming),
                getString(R.string.description_start_streaming),
                onStartLiveStream = {navigateToStreamNowFragment() },
                onScheduleLiveStream = { navigateToScheduleStreamFragment() })
        }

        binding.bottomNavTeacherMain.setOnItemSelectedListener { menuItem ->

            when (menuItem.itemId) {
                R.id.item_teacher_home_fragment -> {
                    binding.toolbar.title = "Main"
                    childFragmentManager.replaceFragment(
                        R.id.fragment_teacher_main_container,
                        TeacherHomeFragment()
                    )
                }

                R.id.item_teacher_profile_fragment -> {
                    binding.toolbar.title = "Profile"
                    childFragmentManager.replaceFragment(
                        R.id.fragment_teacher_main_container,
                        TeacherProfileFragment()
                    )
                }

                else -> UnknownError("Fragment not found for menu item: ${menuItem.title}")
            }
            true
        }
    }

    private fun navigateToScheduleStreamFragment() {
        requireActivity().supportFragmentManager.addFragmentToBackStack(
            R.id.activity_container_view,
            ScheduleStreamFragment()
        )
    }

    private fun navigateToStreamNowFragment() {
        requireActivity().supportFragmentManager.addFragmentToBackStack(
            R.id.activity_container_view,
            StreamNowFragment()
        )
    }

    fun showScheduleOrLiveDialog(
        context: Context,
        title: String,
        message: String,
        onStartLiveStream: () ->  Unit,
        onScheduleLiveStream: () -> Unit
    ) {
        val dialogBuilder = MaterialAlertDialogBuilder(context, R.style.RoundedAlertStyle)

        dialogBuilder.setTitle(title)
            .setMessage(message)
            .setCancelable(true)
            .setNegativeButton(getString(R.string.title_schedule)) { dialog, _ ->
                onScheduleLiveStream()
                dialog.dismiss()
            }
            .setPositiveButton(getString(R.string.title_start_now)) { dialog, _ ->
                onStartLiveStream()
                dialog.dismiss()
            }

        val alert = dialogBuilder.create()
        alert.show()
    }


}
