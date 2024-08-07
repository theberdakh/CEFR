package com.imax.cefr.fragments.entering.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.imax.cefr.data.models.LoginRequestData
import com.imax.cefr.presentation.LoginViewModel
import com.imax.cefr.core.base.pref.LocalStorage
import com.imax.cefr.MainActivity
import com.imax.cefr.R
import com.imax.cefr.core.base.fragment.BaseFragment
import com.imax.cefr.data.models.UserType
import com.imax.cefr.databinding.FragmentSignInBinding
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

data class FakeUser(
    val username: String,
    val password: String = "12345678",
    val fullName: String = "",
    val twitchChannelUsername: String = "",
    val streamToken: String = ""
)

class LoginFragment : BaseFragment<FragmentSignInBinding>(FragmentSignInBinding::inflate) {

    private val localStorage: LocalStorage by inject()
    private lateinit var mainActivity: MainActivity
    private val loginViewModel by viewModel<LoginViewModel>()
    private val fakeUsers
        get() = listOf(
            FakeUser(
                username = "teacher1",
                fullName = "Amir Baymuratov",
                streamToken = "live_509012821_J6GzFWy6vraGlObtJC0XHI7QbLh43v",
                twitchChannelUsername = "amir_b1"
            ), FakeUser(
                username = "teacher2",
                fullName = "Ruslan Joldasbaev",
                streamToken = "live_1103815460_Je3dwgeuSYh27Ne85T9IgIHWvTuoQJ",
                twitchChannelUsername = "user_nukus"
            ), FakeUser(
                username = "teacher3",
                fullName = "Asadbek Qogambaev",
                streamToken = "live_1103829928_ET0hwqJ8vC4vqf7rG7VNW2Xrj9NNy6",
                twitchChannelUsername = "user_xojeli"
            ), FakeUser(
                username = "teacher4",
                fullName = "Nawrizbay Baltabaev",
                streamToken = "live_1103831671_EN0Ej9jg4taRKWDhTt1nTQeXBZuIkq",
                twitchChannelUsername = "user_shomanay"
            ), FakeUser(
                username = "teacher5",
                fullName = "Damir Dilmuratov",
                streamToken = "live_1103834869_DRdaYLWC790kGLxX7bB02gO8Odw0LQ",
                twitchChannelUsername = "user_kegeyli"
            ), FakeUser(
                username = "student1",
                fullName = "Ruslan Joldasbaev"
            )

        )

    override fun FragmentSignInBinding.observeViewModel() {
        loginViewModel.loginFlow.onEach {}
    }

    override fun FragmentSignInBinding.setUpViews() {
        mainActivity = requireActivity() as MainActivity
        mainActivity.settingsBottomNavigation(false)
        mainActivity.settingsBottomNavigationStudent(false)

        icBack.setOnClickListener {
            findNavController().popBackStack()
        }

        tvForgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_forgotPasswordFragment)
        }

        btnSignIn.setOnClickListener {

            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            // loginViewModel.login(LoginRequestData(email, password))

            if (email.isNotEmpty() && password.isNotEmpty()) {

                for (user in fakeUsers) {
                    if (user.username == email && user.password == password) {
                        localStorage.login = user.username
                        localStorage.fullName = user.fullName
                        localStorage.translationName = user.streamToken
                        localStorage.channelName = user.twitchChannelUsername
                        localStorage.isLogin = true

                    }
                    when (localStorage.type) {
                        UserType.TEACHER.token ->
                            findNavController().navigate(R.id.action_signInFragment_to_mainFragment)
                        UserType.STUDENT.token -> findNavController().navigate(
                            R.id.action_signInFragment_to_studentMainFragment
                        )
                        else -> throw UnknownError("User type is not defined")

                    }
                }
            }
        }
    }

    override fun FragmentSignInBinding.navigation() {}

}
