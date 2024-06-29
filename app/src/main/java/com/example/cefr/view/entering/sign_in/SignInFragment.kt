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

    private val localStorage: LocalStorage by inject()
    private lateinit var mainActivity: MainActivity

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSignInBinding.bind(view)
        initVariables()
        setupListeners()

    }

    private fun initVariables() {
        mainActivity = requireActivity() as MainActivity
        mainActivity.settingsBottomNavigation(false)
        mainActivity.settingsBottomNavigationStudent(false)
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
                    if (localStorage.type == "Teacher") {
                        if (email == "teacher1" && password == "12345678") {
                            localStorage.login = "teacher1"
                            localStorage.fullName = "Amir Baymuratov"
                            localStorage.isLogin = true
                            // Amir
                            localStorage.translationName =
                                "live_509012821_J6GzFWy6vraGlObtJC0XHI7QbLh43v"
                            localStorage.channelName = "amir_b1"
                        } else if (email == "teacher2" && password == "12345678") {
                            localStorage.login = "teacher2"
                            localStorage.fullName = "Ruslan Joldasbaev"
                            localStorage.isLogin = true
                            // user_nukus
                            localStorage.translationName =
                                "live_1103815460_Je3dwgeuSYh27Ne85T9IgIHWvTuoQJ"
                            localStorage.channelName = "user_nukus"
                        } else if (email == "teacher3" && password == "12345678") {
                            localStorage.login = "teacher3"
                            localStorage.fullName = "Asadbek Qogambaev"
                            localStorage.isLogin = true
                            // user_xojeli
                            localStorage.translationName =
                                "live_1103829928_ET0hwqJ8vC4vqf7rG7VNW2Xrj9NNy6"
                            localStorage.channelName = "user_xojeli"
                        } else if (email == "teacher4" && password == "12345678") {
                            localStorage.login = "teacher4"
                            localStorage.fullName = "Nawrizbay Baltabaev"
                            localStorage.isLogin = true
                            // user_shomanay
                            localStorage.translationName =
                                "live_1103831671_EN0Ej9jg4taRKWDhTt1nTQeXBZuIkq"
                            localStorage.channelName = "user_shomanay"
                        } else if (email == "teacher5" && password == "12345678") {
                            localStorage.login = "teacher5"
                            localStorage.fullName = "Damir Dilmuratov"
                            localStorage.isLogin = true
                            // user_kegeyli
                            localStorage.translationName =
                                "live_1103834869_DRdaYLWC790kGLxX7bB02gO8Odw0LQ"
                            localStorage.channelName = "user_kegeyli"
                        } else if (email == "student1" && password == "12345678") {
                            localStorage.login = "student1"
                            localStorage.fullName = "Ruslan Joldasbaev"
                            localStorage.isLogin = true
                        } else if (email == "student2" && password == "12345678") {
                            localStorage.login = "student2"
                            localStorage.fullName = "Ruslan Joldasbaev"
                            localStorage.isLogin = true
                        } else if (email == "student3" && password == "12345678") {
                            localStorage.login = "student3"
                            localStorage.fullName = "Ruslan Joldasbaev"
                            localStorage.isLogin = true
                        } else if (email == "student4" && password == "12345678") {
                            localStorage.login = "student4"
                            localStorage.fullName = "Ruslan Joldasbaev"
                            localStorage.isLogin = true
                        } else if (email == "student5" && password == "12345678") {
                            localStorage.login = "student5"
                            localStorage.fullName = "Ruslan Joldasbaev"
                            localStorage.isLogin = true
                        }
                        findNavController().navigate(
                            SignInFragmentDirections.actionSignInFragmentToMainFragment()
                        )
                    } else if (localStorage.type == "Student") {
                        if (email == "student1" && password == "12345678") {
                            localStorage.login = "student1"
                            localStorage.fullName = "Tayrov Azat"
                            localStorage.isLogin = true
                        }
                        findNavController().navigate(
                            SignInFragmentDirections.actionSignInFragmentToStudentMainFragment()
                        )
                    }
                }
            }
        }
    }
}