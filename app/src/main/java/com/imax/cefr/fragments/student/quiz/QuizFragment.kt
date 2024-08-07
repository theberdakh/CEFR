package com.imax.cefr.fragments.student.quiz

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.imax.cefr.R
import com.imax.cefr.databinding.FragmentTestBinding

class QuizFragment : Fragment(R.layout.fragment_test) {

    private lateinit var binding: FragmentTestBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTestBinding.bind(view)



    }
}
