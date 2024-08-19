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
import com.imax.cefr.core.base.resource.Resource
import com.imax.cefr.data.models.UserType
import com.imax.cefr.data.models.login.LoginRequestData
import com.imax.cefr.presentation.LoginViewModel
import com.imax.cefr.utils.toastMessage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var binding: FragmentSplashBinding
    private val localStorage: LocalStorage by inject()
    private val loginViewModel by viewModel<LoginViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSplashBinding.bind(view)
        initObservers()
        setupData()

    }

    private fun initObservers() {
        loginViewModel.loginFlow.onEach { result ->
            when (result) {
                is Resource.Success -> {
                    result.data?.let { loginResponseData ->
                        localStorage.storeToken(loginResponseData.token)
                    }

                    result.data?.user?.let { user ->
                        localStorage.storeUser(user)
                        localStorage.storeLogin(true)
                    }
                    requireActivity().supportFragmentManager.changeNavGraph(
                        R.id.activity_container_view,
                        R.navigation.teacher_nav
                    )
                }

                is Resource.Error -> {
                    localStorage.storeLogin(true)
                    toastMessage("${result.error}")
                }

                Resource.Loading -> {
                    Log.d("LoginFragment", "Loading")
                }
            }
        }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setupData() {
        binding.infoLogo.playAnimation()

        viewLifecycleOwner.lifecycleScope.launch {
            delay(2000)

            if (localStorage.isLoggedIn()) {
                loginViewModel.login(
                    LoginRequestData(
                        email = localStorage.getUser().email,
                        password = localStorage.getPassword()
                    )
                )
                binding.infoLogo.pauseAnimation()
            } else {
                requireActivity().supportFragmentManager.changeNavGraph(
                    R.id.activity_container_view,
                    R.navigation.login_nav
                )
                binding.infoLogo.pauseAnimation()
            }


        }



    }


}
