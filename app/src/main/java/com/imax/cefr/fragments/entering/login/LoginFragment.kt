package com.imax.cefr.fragments.entering.login

import com.imax.cefr.R
import com.imax.cefr.core.base.fragment.BaseFragment
import com.imax.cefr.core.base.fragment.changeNavGraph
import com.imax.cefr.core.base.pref.LocalStorage
import com.imax.cefr.data.models.UserType
import com.imax.cefr.databinding.FragmentLoginBinding
import com.imax.cefr.presentation.LoginViewModel
import com.imax.cefr.utils.toastMessage
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

data class FakeUser(
    val username: String,
    val password: String = "12345678",
    val fullName: String = "",
    val twitchChannelUsername: String = "",
    val streamKey: String = "",
    val userType: String = UserType.TEACHER.token
)

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val localStorage: LocalStorage by inject()
    private val loginViewModel by viewModel<LoginViewModel>()
    private val fakeUsers
        get() = listOf(
            FakeUser(
                username = "teacher1",
                fullName = "Amir Baymuratov",
                streamKey = "live_509012821_J6GzFWy6vraGlObtJC0XHI7QbLh43v",
                twitchChannelUsername = "amir_b1"
            ), FakeUser(
                username = "teacher2",
                fullName = "Ruslan Joldasbaev",
                streamKey = "live_1103815460_Je3dwgeuSYh27Ne85T9IgIHWvTuoQJ",
                twitchChannelUsername = "user_nukus"
            ), FakeUser(
                username = "teacher3",
                fullName = "Asadbek Qogambaev",
                streamKey = "live_1103829928_ET0hwqJ8vC4vqf7rG7VNW2Xrj9NNy6",
                twitchChannelUsername = "user_xojeli"
            ), FakeUser(
                username = "teacher4",
                fullName = "Nawrizbay Baltabaev",
                streamKey = "live_1103831671_EN0Ej9jg4taRKWDhTt1nTQeXBZuIkq",
                twitchChannelUsername = "user_shomanay"
            ), FakeUser(
                username = "teacher5",
                fullName = "Damir Dilmuratov",
                streamKey = "live_1103834869_DRdaYLWC790kGLxX7bB02gO8Odw0LQ",
                twitchChannelUsername = "user_kegeyli"
            ), FakeUser(
                username = "student1",
                fullName = "Ruslan Joldasbaev",
                userType = UserType.STUDENT.token
            )

        )

    override fun FragmentLoginBinding.observeViewModel() {
        loginViewModel.loginFlow.onEach {}
    }

    override fun FragmentLoginBinding.setUpViews() {

        btnSignIn.setOnClickListener {

            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            // loginViewModel.login(LoginRequestData(email, password))

            if (email.isNotEmpty() && password.isNotEmpty()) {
                for (user in fakeUsers) {
                    if (user.username == email && user.password == password) {
                        localStorage.login = user.username
                        localStorage.fullName = user.fullName
                        localStorage.streamKey = user.streamKey
                        localStorage.twitchChannelUsername = user.twitchChannelUsername
                        localStorage.isLoggedIn = true
                        localStorage.type = user.userType

                        val navGraph = when (localStorage.type) {
                            UserType.TEACHER.token -> R.navigation.teacher_nav
                            UserType.STUDENT.token -> R.navigation.student_nav
                            else -> throw UnknownError("Unknown UserType")
                        }
                        requireActivity().supportFragmentManager.changeNavGraph(
                            R.id.activity_container_view,
                            navGraph
                        )
                        break
                    } else {
                        toastMessage("User not found")
                    }
                }
            } else {
                toastMessage("Please fill all fields")
            }


        }
    }

    override fun FragmentLoginBinding.navigation() {}

}
