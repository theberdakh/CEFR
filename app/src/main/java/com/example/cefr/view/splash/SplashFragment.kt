package com.example.cefr.view.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.cefr.R
import com.example.cefr.databinding.FragmentSplashBinding
import com.example.cefr.utils.LocalStorage
import com.example.cefr.view.MainActivity
import kotlinx.coroutines.delay
import org.koin.android.ext.android.inject

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var binding: FragmentSplashBinding
    private lateinit var mainActivity: MainActivity
    private val localStorage: LocalStorage by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSplashBinding.bind(view)
        initVariables()
        setupData()

    }

    private fun initVariables() {
        mainActivity = requireActivity() as MainActivity
        mainActivity.settingsBottomNavigation(false)
    }

    private fun setupData() {
        binding.infoLogo.playAnimation()
        Handler().postDelayed({
            if (localStorage.isLogin) {
                findNavController().navigate(
                    SplashFragmentDirections.actionSplashFragmentToMainFragment()
                )
            } else {
                findNavController().navigate(
                    SplashFragmentDirections.actionSplashFragmentToGridFragment()
                )
            }
        }, 2600)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity.settingsBottomNavigation(true)
    }
}