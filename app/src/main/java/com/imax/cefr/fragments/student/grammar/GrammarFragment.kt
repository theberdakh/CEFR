package com.imax.cefr.fragments.student.grammar

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.imax.cefr.R
import com.imax.cefr.databinding.FragmentGrammarBinding
import com.imax.cefr.MainActivity

class GrammarFragment : Fragment(R.layout.fragment_grammar) {
    private lateinit var binding: FragmentGrammarBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentGrammarBinding.bind(view)

        initVariables()

        binding.icBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.cardOne.setOnClickListener {
        }
    }

    private fun initVariables() {}

}
