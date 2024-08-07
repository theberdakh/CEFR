package com.imax.cefr.fragments.student.reading

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.imax.cefr.R
import com.imax.cefr.databinding.FragmentReadingBinding
import com.imax.cefr.MainActivity

class ReadingFragment : Fragment(R.layout.fragment_reading) {

    private lateinit var binding: FragmentReadingBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentReadingBinding.bind(view)

        binding.icBack.setOnClickListener {
            findNavController().popBackStack()
        }

    }
}
