package com.imax.cefr.fragments.student.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.imax.cefr.R
import com.imax.cefr.core.base.fragment.replaceFragment
import com.imax.cefr.databinding.FragmentStudentMainBinding
import com.imax.cefr.fragments.student.home.StudentHomeFragment
import com.imax.cefr.fragments.student.profile.StudentProfileFragment

class StudentMainFragment: Fragment() {

    private lateinit var binding: FragmentStudentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStudentMainBinding.inflate(inflater, container, false)
        childFragmentManager.replaceFragment(R.id.fragment_student_main_container, StudentHomeFragment())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fabStudentMain.setOnClickListener {
            childFragmentManager.replaceFragment(
                R.id.fragment_student_main_container,
                StudentHomeFragment()
            )
            binding.toolbar.title = "Main"
        }
        binding.bottomNavStudentMain.setOnItemSelectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.item_menu_student_rating -> {
                    binding.toolbar.title = "Rating"
                    //Replace to StudentRatingFragment()
                }
                R.id.item_menu_student_profile -> {
                    childFragmentManager.replaceFragment(
                        R.id.fragment_student_main_container,
                        StudentProfileFragment()
                    )
                    binding.toolbar.title = "Profile"
                }
                else -> UnknownError("Fragment not found for menu item: ${menuItem.title}")
            }
            true
        }
    }
}
