package com.example.cefr

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.cefr.databinding.FragmentPreviousBinding

class PreviousFragment : Fragment(R.layout.fragment_previous) {

    private lateinit var binding: FragmentPreviousBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPreviousBinding.bind(view)


    }
}