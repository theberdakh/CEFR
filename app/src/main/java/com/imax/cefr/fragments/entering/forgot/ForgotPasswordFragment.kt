package com.imax.cefr.fragments.entering.forgot

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.imax.cefr.R
import com.imax.cefr.databinding.FragmentForgotPasswordBinding

class ForgotPasswordFragment : Fragment(R.layout.fragment_forgot_password) {

    private lateinit var binding: FragmentForgotPasswordBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentForgotPasswordBinding.bind(view)
    }
}
