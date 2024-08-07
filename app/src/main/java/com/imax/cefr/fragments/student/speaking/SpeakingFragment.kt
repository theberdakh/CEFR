package com.imax.cefr.fragments.student.speaking

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.imax.cefr.R
import com.imax.cefr.databinding.FragmentSpeakingBinding
import com.imax.cefr.MainActivity

class SpeakingFragment : Fragment(R.layout.fragment_speaking) {

    private lateinit var binding: FragmentSpeakingBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSpeakingBinding.bind(view)
        binding.icBack.setOnClickListener {
            findNavController().popBackStack()
        }

    }
}
