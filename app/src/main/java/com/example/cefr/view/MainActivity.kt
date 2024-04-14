package com.example.cefr.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.cefr.LiveActivity
import com.example.cefr.R
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
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_controller) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        binding.fab.setOnClickListener {
            val intent = Intent(this, LiveActivity::class.java)
            startActivity(intent)
        }
    }

}