package com.imax.cefr.fragments.student.speaking

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.imax.cefr.R
import com.imax.cefr.databinding.FragmentSpeakingBinding
import com.imax.cefr.fragments.MainActivity

class SpeakingFragment : Fragment(R.layout.fragment_speaking) {

    private lateinit var binding: FragmentSpeakingBinding
    private val navArgs by navArgs<SpeakingFragmentArgs>()
    private lateinit var mainActivity: MainActivity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSpeakingBinding.bind(view)

        binding.tvTitle.text = navArgs.title

        initVariables()

        binding.icBack.setOnClickListener {
            findNavController().popBackStack()
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
