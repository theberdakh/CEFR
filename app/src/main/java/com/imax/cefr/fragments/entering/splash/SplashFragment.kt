package com.imax.cefr.fragments.entering.splash

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.imax.cefr.R
import com.imax.cefr.databinding.FragmentSplashBinding
import com.imax.cefr.core.base.pref.LocalStorage
import com.imax.cefr.MainActivity
import com.imax.cefr.core.base.fragment.changeNavGraph
import com.imax.cefr.data.models.UserType
import org.koin.android.ext.android.inject

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var binding: FragmentSplashBinding
    private val localStorage: LocalStorage by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSplashBinding.bind(view)
        setupData()

    }

    private fun setupData() {
        binding.infoLogo.playAnimation()

        Handler().postDelayed({

            Log.d("SplashFragment", "${localStorage.getUser().role}" )

            val navGraph = when(localStorage.getUser().role){
                UserType.TEACHER.token -> R.navigation.teacher_nav
                UserType.STUDENT.token -> R.navigation.student_nav
                else -> R.navigation.login_nav
            }
            requireActivity().supportFragmentManager.changeNavGraph(R.id.activity_container_view, navGraph)

        }, 2000)
    }


}
