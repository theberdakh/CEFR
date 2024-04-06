package com.example.cefr

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.cefr.databinding.FragmentTeacherBinding

class TeacherFragment : Fragment(R.layout.fragment_teacher) {

    private lateinit var binding: FragmentTeacherBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTeacherBinding.bind(view)
    }
}