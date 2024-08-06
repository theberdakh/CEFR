package com.imax.cefr.fragments.teacher.previous

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.imax.cefr.R
import com.imax.cefr.databinding.FragmentPreviousBinding

class PreviousFragment : Fragment(R.layout.fragment_previous) {

    private lateinit var binding: FragmentPreviousBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPreviousBinding.bind(view)


    }
}
