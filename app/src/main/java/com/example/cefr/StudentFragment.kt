package com.example.cefr

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.cefr.databinding.FragmentStudentBinding

class StudentFragment : Fragment(R.layout.fragment_student) {

    private lateinit var binding: FragmentStudentBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentStudentBinding.bind(view)
    }
}