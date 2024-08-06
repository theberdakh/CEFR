package com.example.cefr.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cefr.R
import com.example.cefr.databinding.FragmentMainStudentBinding
import com.example.cefr.utils.LocalStorage
import org.koin.android.ext.android.inject

class StudentMainFragment : Fragment(R.layout.fragment_main_student) {

    private lateinit var binding: FragmentMainStudentBinding
    private lateinit var mainActivity: MainActivity
    private val localStorage: LocalStorage by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainStudentBinding.bind(view)

        initVariables()
        setupListeners()
    }

    private fun initVariables() {
        mainActivity = requireActivity() as MainActivity
        mainActivity.settingsBottomNavigation(false)
        mainActivity.settingsBottomNavigationStudent(true)
    }

    private fun setupListeners() {
        binding.btnGrammar.setOnClickListener {
            findNavController().navigate(
                StudentMainFragmentDirections.actionStudentMainFragmentToGrammarFragment("Grammar")
            )
        }

        binding.btnReading.setOnClickListener {
            findNavController().navigate(
                StudentMainFragmentDirections.actionStudentMainFragmentToReadingFragment("Reading")
            )
        }

        binding.btnListening.setOnClickListener {
            findNavController().navigate(
                StudentMainFragmentDirections.actionStudentMainFragmentToListeningFragment("Listening")
            )
        }

        binding.btnWriting.setOnClickListener {
            findNavController().navigate(
                StudentMainFragmentDirections.actionStudentMainFragmentToWritingFragment("Writing")
            )
        }

        binding.btnSpeaking.setOnClickListener {
            findNavController().navigate(
                StudentMainFragmentDirections.actionStudentMainFragmentToSpeakingFragment("Speaking")
            )
        }
    }
}
