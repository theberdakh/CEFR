package com.example.cefr.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.cefr.R
import com.example.cefr.databinding.FragmentProfileStudentBinding
import com.example.cefr.utils.LocalStorage
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