package com.example.cefr.view.entering.grid

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.cefr.R
import com.example.cefr.databinding.FragmentGridBinding
import com.example.cefr.utils.Constants
import com.example.cefr.view.MainActivity

class GridFragment : Fragment(R.layout.fragment_grid) {
    private lateinit var binding: FragmentGridBinding
    private lateinit var mainActivity: MainActivity
    private val gridViewPagerAdapter = GridViewPagerAdapter()
    private var currentId = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGridBinding.bind(view)

        initVariables()

        initListeners()

        initObservers()

    }

    private fun initVariables() {
        mainActivity = requireActivity() as MainActivity
        mainActivity.settingsBottomNavigation(false)
        mainActivity.settingsBottomNavigationStudent(false)
        binding.viewPager.adapter = gridViewPagerAdapter
    }

    private fun initObservers() {

    }

    private fun initListeners() {
        gridViewPagerAdapter.submitList(Constants.gridImageList)

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

