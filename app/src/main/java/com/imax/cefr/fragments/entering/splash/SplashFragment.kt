package com.imax.cefr.fragments.entering.splash

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.imax.cefr.R
import com.imax.cefr.databinding.FragmentSplashBinding
import com.imax.cefr.core.base.pref.LocalStorage
import com.imax.cefr.MainActivity
import com.imax.cefr.core.base.fragment.changeNavGraph
import com.imax.cefr.data.models.UserType
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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

        viewLifecycleOwner.lifecycleScope.launch {
            delay(1800)

            val navGraph = if (localStorage.isLoggedIn()) {
                R.navigation.teacher_nav
            } else R.navigation.login_nav

            requireActivity().supportFragmentManager.changeNavGraph(
                R.id.activity_container_view,
                navGraph
            )
        }

    }


}
