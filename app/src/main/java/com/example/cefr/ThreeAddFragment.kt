package com.example.cefr

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.cefr.databinding.FragmentAddThreeBinding

class ThreeAddFragment : Fragment(R.layout.fragment_add_three) {

    private lateinit var binding: FragmentAddThreeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAddThreeBinding.bind(view)

    }
}