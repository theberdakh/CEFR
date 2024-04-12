package com.example.cefr.view.entering.sign_in

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.cefr.R
import com.example.cefr.databinding.FragmentSignInBinding

class SignInFragment : Fragment(R.layout.fragment_sign_in) {

    private lateinit var binding: FragmentSignInBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSignInBinding.bind(view)
    }
}