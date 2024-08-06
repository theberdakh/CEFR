package com.imax.cefr.fragments.teacher.splash

import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.imax.cefr.R
import com.imax.cefr.databinding.FragmentSplashBinding
import com.imax.cefr.utils.LocalStorage
import com.imax.cefr.fragments.MainActivity
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
            if (localStorage.type == "Teacher") {
                if (localStorage.isLogin) {
                    findNavController().navigate(
                        SplashFragmentDirections.actionSplashFragmentToMainFragment()
                    )
                } else {
                    findNavController().navigate(
                        SplashFragmentDirections.actionSplashFragmentToGridFragment()
                    )
                }
            } else if (localStorage.type == "Student") {
                if (localStorage.isLogin) {
                    findNavController().navigate(
                        SplashFragmentDirections.actionSplashFragmentToStudentMainFragment()
                    )
                } else {
                    findNavController().navigate(
                        SplashFragmentDirections.actionSplashFragmentToGridFragment()
                    )
                }
            } else {
                findNavController().navigate(
                    SplashFragmentDirections.actionSplashFragmentToGridFragment()
                )
            }
        }, 2600)
    }
}
