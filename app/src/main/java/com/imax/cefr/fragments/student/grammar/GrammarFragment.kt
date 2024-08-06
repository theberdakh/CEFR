package com.imax.cefr.fragments.student.grammar

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.imax.cefr.R
import com.imax.cefr.databinding.FragmentGrammarBinding
import com.imax.cefr.fragments.MainActivity

class GrammarFragment : Fragment(R.layout.fragment_grammar) {
    private lateinit var binding: FragmentGrammarBinding
    private val navArgs by navArgs<GrammarFragmentArgs>()
    private lateinit var mainActivity: MainActivity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentGrammarBinding.bind(view)

        binding.tvTitle.text = navArgs.title

        initVariables()

        binding.icBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.cardOne.setOnClickListener {
            findNavController().navigate(
                GrammarFragmentDirections.actionGrammarFragmentToTestFragment()
            )
        }
    }

    private fun initVariables() {
        mainActivity = requireActivity() as MainActivity
        mainActivity.settingsBottomNavigation(false)
        mainActivity.settingsBottomNavigationStudent(false)
    }


    override fun onDestroy() {
        super.onDestroy()
        mainActivity.settingsBottomNavigation(false)
        mainActivity.settingsBottomNavigationStudent(true)
    }
}
