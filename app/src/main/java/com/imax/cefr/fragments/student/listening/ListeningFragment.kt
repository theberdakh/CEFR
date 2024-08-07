package com.imax.cefr.fragments.student.listening

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.imax.cefr.R
import com.imax.cefr.databinding.FragmentListeningBinding
import com.imax.cefr.MainActivity

class ListeningFragment : Fragment(R.layout.fragment_listening) {

    private lateinit var binding: FragmentListeningBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentListeningBinding.bind(view)



        binding.icBack.setOnClickListener{
            findNavController().popBackStack()
        }

    }


    override fun onDestroy() {
        super.onDestroy()
    }
}
