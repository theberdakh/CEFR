package com.imax.cefr.fragments.student.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.imax.cefr.R
import com.imax.cefr.databinding.FragmentProfileStudentBinding
import com.imax.cefr.MainActivity
import com.imax.cefr.core.base.pref.LocalStorage
import org.koin.android.ext.android.inject

class StudentProfileFragment : Fragment(R.layout.fragment_profile_student) {

    private lateinit var binding: FragmentProfileStudentBinding
    private val localStorage: LocalStorage by inject()
    private lateinit var mainActivity: MainActivity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProfileStudentBinding.bind(view)

        mainActivity = requireActivity() as MainActivity
        mainActivity.settingsBottomNavigationStudent(true)
        mainActivity.settingsBottomNavigation(false)

        binding.tvOne.text = localStorage.fullName

    }
}
