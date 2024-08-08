package com.imax.cefr.fragments.student.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.imax.cefr.R
import com.imax.cefr.databinding.FragmentStudentProfileBinding
import com.imax.cefr.core.base.pref.LocalStorage
import org.koin.android.ext.android.inject

class StudentProfileFragment : Fragment(R.layout.fragment_student_profile) {

    private lateinit var binding: FragmentStudentProfileBinding
    private val localStorage: LocalStorage by inject()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentStudentProfileBinding.bind(view)


        binding.tvOne.text = localStorage.fullName

    }
}
