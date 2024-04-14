package com.example.cefr.view.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.cefr.R
import com.example.cefr.databinding.FragmentMainBinding
import com.example.cefr.view.MainActivity

class MainFragment : Fragment(R.layout.fragment_main) {

    private lateinit var binding: FragmentMainBinding
    private lateinit var mainActivity: MainActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)
        initVariables()

    }

    private fun initVariables() {
        mainActivity = requireActivity() as MainActivity
        mainActivity.settingsBottomNavigation(true)
    }
}