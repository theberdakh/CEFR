package com.imax.cefr.fragments.entering.login

import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.imax.cefr.R
import com.imax.cefr.core.base.fragment.BaseFragment
import com.imax.cefr.core.base.fragment.changeNavGraph
import com.imax.cefr.core.base.pref.LocalStorage
import com.imax.cefr.core.base.resource.Resource
import com.imax.cefr.data.models.UserType
import com.imax.cefr.data.models.login.LoginRequestData
import com.imax.cefr.data.models.login.LoginResponseData
import com.imax.cefr.data.models.login.UserRole
import com.imax.cefr.databinding.FragmentLoginBinding
import com.imax.cefr.presentation.LoginViewModel
import com.imax.cefr.utils.toastMessage
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : BaseFragment<FragmentLoginBinding>(FragmentLoginBinding::inflate) {

    private val loginViewModel by viewModel<LoginViewModel>()

    private fun navigateByRole(role: String) {
        val navGraph = when (role) {
            UserRole.TEACHER.role -> R.navigation.teacher_nav
            UserRole.STUDENT.role -> R.navigation.student_nav
            else -> throw UnknownError("Unknown UserRule: ${localStorage.getUser().role}")
        }
        requireActivity().supportFragmentManager.changeNavGraph(
            R.id.activity_container_view,
            navGraph
        )
    }

    override fun FragmentLoginBinding.observeViewModel() {
        loginViewModel.loginFlow.onEach { result ->
            when(result) {
                is Resource.Success -> {
                    Log.d("LoginFragment", "Success ${result.data}")
                    result.data?.let { loginResponseData ->
                        localStorage.storeToken(loginResponseData.token)
                    }

                    result.data?.user?.let {
                        user -> localStorage.storeUser(user)
                        localStorage.storeLogin(true)
                        Log.d("LoginFragment", "Role: ${user.role}")
                        navigateByRole(result.value.user.role)
                    }

                }
                is Resource.Error -> {
                    Log.d("LoginFragment", "Error ${result.data}")
                    toastMessage("${result.error}")}
                Resource.Loading -> {
                    Log.d("LoginFragment", "Loading")}
            }
        }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    override fun FragmentLoginBinding.setUpViews() {

        btnSignIn.setOnClickListener {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            loginViewModel.login(LoginRequestData(email = email, password = password))
        }
    }

    override fun FragmentLoginBinding.navigation() {}

}
