package com.example.cefr

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.cefr.databinding.FragmentAddOneBinding

class OneAddFragment : Fragment(R.layout.fragment_add_one) {

    private lateinit var binding: FragmentAddOneBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentAddOneBinding.bind(view)

    }
}