package com.example.cefr.view.entering.sign_in

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.cefr.R
import com.example.cefr.data.models.LoginRequestData
import com.example.cefr.databinding.FragmentSignInBinding
import com.example.cefr.presentation.LoginViewModel
import com.example.cefr.utils.LocalStorage
import com.example.cefr.utils.snackBar
import com.example.cefr.utils.toastMessage
import com.example.cefr.view.MainActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private lateinit var binding: FragmentSignInBinding

    private val viewModel by viewModel<LoginViewModel>()
    private val localStorage: LocalStorage by inject()
    private lateinit var mainActivity: MainActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSignInBinding.bind(view)
        initVariables()
        setupListeners()
        initObservers()

    }

    private fun initVariables() {
        mainActivity = requireActivity() as MainActivity
        mainActivity.settingsBottomNavigation(false)
    }


    private fun setupListeners() {

        with(binding) {
            tvForgotPassword.setOnClickListener {
                findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToForgotPasswordFragment())
            }

            btnSignIn.setOnClickListener {
                val email = etEmail.text.toString()
                val password = etPassword.text.toString()


                if (email.isNotEmpty() && password.isNotEmpty()) {
                    viewModel.login(
                        LoginRequestData(
                            email, password
                        )
                    )
                } else {
                    toastMessage("Email yaki parol qáte")
                }

            }
        }
    }

    private fun initObservers() {
        viewModel.loginResult.onEach { result ->
            result.onSuccess {
                localStorage.isLogin = true
                localStorage.token = it.token
                findNavController().navigate(SignInFragmentDirections.actionSignInFragmentToMainFragment())
            }

            result.onFailure {
                snackBar(it.localizedMessage)
            }

        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity.settingsBottomNavigation(true)
    }
}