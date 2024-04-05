package com.example.cefr

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.cefr.databinding.FragmentAddTwoBinding

class TwoAddFragment : Fragment(R.layout.fragment_add_two) {

    private lateinit var binding: FragmentAddTwoBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAddTwoBinding.bind(view)

    }
}