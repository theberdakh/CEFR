package com.imax.cefr.fragments.teacher.splash

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.imax.cefr.R
import com.imax.cefr.databinding.FragmentSplashBinding
import com.imax.cefr.core.base.pref.LocalStorage
import com.imax.cefr.MainActivity
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
        mainActivity.settingsBottomNavigationStudent(false)
    }

    private fun setupData() {
        binding.infoLogo.playAnimation()

        Handler().postDelayed({
            Log.d("SplashFragment", "${localStorage.isLogin}")
            Log.d("SplashFragment", "${localStorage.type}")
            if (localStorage.isLogin){
                when(localStorage.type){
                    "Teacher" -> findNavController().navigate(
                        R.id.action_splashFragment_to_mainFragment
                    )
                    "Student" -> findNavController().navigate(R.id.action_splashFragment_to_studentMainFragment)
                }
            } else {
                findNavController().navigate(R.id.action_splashFragment_to_gridFragment)
            }
        }, 2600)
    }
}
