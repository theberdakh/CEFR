package com.example.cefr.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cefr.databinding.ActivityMainBinding
import com.example.cefr.utils.gone
import com.example.cefr.utils.visible

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBottomNavigation()

    }

    fun settingsBottomNavigation(show: Boolean) {
        if (show) {
            binding.bottomAppBar.visible()
            binding.fab.visible()
        } else {
            binding.fab.gone()
            binding.bottomAppBar.gone()
        }
    }

    private fun setBottomNavigation() {
        binding.bottomNavigationView.background = null
        binding.bottomNavigationView.menu.getItem(1).isEnabled = false
    }

}