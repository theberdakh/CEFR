package com.example.cefr

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.cefr.databinding.FragmentInformationBinding

class InformationFragment : Fragment(R.layout.fragment_information) {

    private lateinit var binding: FragmentInformationBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentInformationBinding.bind(view)

    }
}