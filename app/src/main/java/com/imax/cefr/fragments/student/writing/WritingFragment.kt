package com.imax.cefr.fragments.student.writing

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.imax.cefr.R
import com.imax.cefr.databinding.FragmentListeningBinding
import com.imax.cefr.MainActivity

class WritingFragment : Fragment(R.layout.fragment_writing) {

    private lateinit var binding: FragmentListeningBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentListeningBinding.bind(view)
        binding.icBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}
