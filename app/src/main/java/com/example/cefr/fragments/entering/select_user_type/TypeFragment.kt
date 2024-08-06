package com.example.cefr.fragments.entering.select_user_type

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.cefr.R
import com.example.cefr.databinding.FragmentTypeBinding
import com.example.cefr.utils.LocalStorage
import com.example.cefr.utils.getColor
import com.example.cefr.fragments.MainActivity
import org.koin.android.ext.android.inject

class TypeFragment : Fragment(R.layout.fragment_type) {
    private lateinit var binding: FragmentTypeBinding
    private var count = 1
    private var lastSelectedItem = -1
    private lateinit var mainActivity: MainActivity
    private val localStorage: LocalStorage by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentTypeBinding.bind(view)
        initVariables()
        initListeners()

    }

    private fun initVariables() {
        mainActivity = requireActivity() as MainActivity
        mainActivity.settingsBottomNavigation(false)
    }

    private fun initListeners() {
        binding.btnTeacher.setActive(false)
        binding.btnStudent.setActive(false)
        count = 0

        binding.btnTeacher.setOnClickListener {
            setTypeImageAndText(false)
            binding.btnTeacher.setActive(true)
            binding.btnStudent.setActive(false)
            if (lastSelectedItem == 0 && count == 1) {
                findNavController().navigate(R.id.action_typeFragment_to_signInFragment)
                lastSelectedItem = -1
                count = 0
            } else if (lastSelectedItem == 1) {
                count = 0
            }
            count++
            lastSelectedItem = 0
        }

        binding.btnStudent.setOnClickListener {
            setTypeImageAndText(true)
            binding.btnStudent.setActive(true)
            binding.btnTeacher.setActive(false)
            if (lastSelectedItem == 1 && count == 1) {
                findNavController().navigate(R.id.action_typeFragment_to_signInFragment)
                lastSelectedItem = -1
                count = 0
            } else if (lastSelectedItem == 0) {
                count = 0
            }
            count++
            lastSelectedItem = 1
        }
    }

    private fun AppCompatButton.setActive(isActive: Boolean) {
        this.isSelected = isActive
        this.setTextColor(
            if (isActive) getColor(
                requireActivity(),
                R.color.brand_color_first
            ) else getColor(requireActivity(), R.color.brand_color_second)
        )
    }

    private fun setTypeImageAndText(type: Boolean) {
        if (type) {
            binding.ivAdd.setImageResource(R.drawable.professor_amico)
            binding.tvAddOne.setText(R.string.teacher)
            binding.tvAddTwo.setText(R.string.teacher_desc)
            localStorage.type = "Teacher"
        } else {
            binding.ivAdd.setImageResource(R.drawable.dictionary_amico)
            binding.tvAddOne.setText(R.string.student)
            binding.tvAddTwo.setText(R.string.student_desc)
            localStorage.type = "Student"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity.settingsBottomNavigation(false)
        mainActivity.settingsBottomNavigationStudent(false)
    }
}
