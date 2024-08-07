package com.imax.cefr.fragments.student.home

import com.imax.cefr.core.base.fragment.BaseFragment
import com.imax.cefr.databinding.FragmentStudentHomeBinding

class StudentHomeFragment : BaseFragment<FragmentStudentHomeBinding>(FragmentStudentHomeBinding::inflate) {


    override fun FragmentStudentHomeBinding.observeViewModel() {}

    override fun FragmentStudentHomeBinding.setUpViews() {
        btnGrammar.setOnClickListener {
            /* findNavController().navigate(
                 StudentMainFragmentDirections.actionStudentMainFragmentToGrammarFragment("Grammar")
             )*/
        }

        btnReading.setOnClickListener {
            /* findNavController().navigate(
                 StudentMainFragmentDirections.actionStudentMainFragmentToReadingFragment("Reading")
             )*/
        }

        btnListening.setOnClickListener {
            /*  findNavController().navigate(
                  StudentMainFragmentDirections.actionStudentMainFragmentToListeningFragment("Listening")
              )*/
        }

        btnWriting.setOnClickListener {
            /*findNavController().navigate(
                StudentMainFragmentDirections.actionStudentMainFragmentToWritingFragment("Writing")
            )*/
        }

        btnSpeaking.setOnClickListener {
            /* findNavController().navigate(
                 StudentMainFragmentDirections.actionStudentMainFragmentToSpeakingFragment("Speaking")
             )*/
        }
    }

    override fun FragmentStudentHomeBinding.navigation() {}


}
