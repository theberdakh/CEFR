package com.imax.cefr.fragments.entering.confirm

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.imax.cefr.R
import com.imax.cefr.databinding.FragmentForgotPasswordBinding

class ConfirmFragment : Fragment(R.layout.fragment_confirm) {

    private lateinit var binding: FragmentForgotPasswordBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentForgotPasswordBinding.bind(view)
    }
}
