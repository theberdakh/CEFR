package com.example.cefr

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.cefr.databinding.FragmentLiveBinding

class LiveFragment : Fragment(R.layout.fragment_live) {
    private lateinit var binding: FragmentLiveBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLiveBinding.bind(view)

        initListeners()

        initObservers()

    }

    private fun initListeners() {

    }

    private fun initObservers() {

    }
}