package com.imax.cefr.fragments.entering.onboarding

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.imax.cefr.R
import com.imax.cefr.databinding.FragmentGridBinding
import com.imax.cefr.MainActivity
import com.imax.cefr.data.models.OnBoardingData

class OnBoardingFragment : Fragment(R.layout.fragment_grid) {
    private lateinit var binding: FragmentGridBinding
    private lateinit var mainActivity: MainActivity
    private val onBoardingViewPagerAdapter = OnBoardingViewPagerAdapter()
    private var currentId = 0

    private val onBoardingDataList = listOf(
        OnBoardingData(
            0,
            R.drawable.ic_add_1,
            "Confidence",
            "With speaking lessons, you will speak in a\nshort time"
        ),
        OnBoardingData(
            1,
            R.drawable.ic_add_2,
            "Patience",
            "Take your time to learn. Develop a habit and\nmake it a part of your daily life"
        ),
        OnBoardingData(
            2,
            R.drawable.ic_add_3,
            "Modern Security",
            "We will provide you with the best and most\nmodern security"
        ),
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGridBinding.bind(view)

        initVariables()
        initListeners()

    }

    private fun initVariables() {
        mainActivity = requireActivity() as MainActivity
        mainActivity.settingsBottomNavigation(false)
        mainActivity.settingsBottomNavigationStudent(false)
        binding.viewPager.adapter = onBoardingViewPagerAdapter
    }

    private fun initListeners() {
        onBoardingViewPagerAdapter.submitList(onBoardingDataList)

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                currentId = position
                position.setIndicator()
            }
        })

        binding.btnNext.setOnClickListener {
            if (currentId != 2) {
                binding.viewPager.setCurrentItem(++currentId, true)
            } else {
                findNavController().navigate(R.id.action_gridFragment_to_typeFragment)
            }
        }

    }

    private fun Int.setIndicator() {
        when (this) {
            0 -> {
                binding.dot1.setImageResource(R.color.brand_color_second)
                binding.dot2.setImageResource(R.color.gray)
                binding.dot3.setImageResource(R.color.gray)
            }

            1 -> {
                binding.dot1.setImageResource(R.color.gray)
                binding.dot2.setImageResource(R.color.brand_color_second)
                binding.dot3.setImageResource(R.color.gray)
            }

            else -> {
                binding.dot1.setImageResource(R.color.gray)
                binding.dot2.setImageResource(R.color.gray)
                binding.dot3.setImageResource(R.color.brand_color_second)
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        mainActivity.settingsBottomNavigation(false)
        mainActivity.settingsBottomNavigationStudent(false)
    }
}

