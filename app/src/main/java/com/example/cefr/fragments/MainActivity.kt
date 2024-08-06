package com.example.cefr.fragments

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.cefr.HomeActivity
import com.example.cefr.LiveActivity
import com.example.cefr.R
import com.example.cefr.databinding.ActivityMainBinding
import com.example.cefr.utils.gone
import com.example.cefr.utils.visible

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Test 3

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBottomNavigation()
        setBottomNavigationStudent()

    }

    fun settingsBottomNavigation(show: Boolean) {
        if (show) {
            binding.bottomAppBar.visible()
            binding.bottomNavigationView.visible()
            binding.fab.visible()
        } else {
            binding.fab.gone()
            binding.bottomNavigationView.gone()
            binding.bottomAppBar.gone()
        }
    }

    fun settingsBottomNavigationStudent(show: Boolean) {
        if (show) {
            binding.bottomAppBarStudent.visible()
            binding.bottomNavigationViewStudent.visible()
            binding.fabStudent.visible()
        } else {
            binding.fabStudent.gone()
            binding.bottomNavigationViewStudent.gone()
            binding.bottomAppBarStudent.gone()
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

    private fun setBottomNavigationStudent() {
        binding.bottomNavigationViewStudent.background = null
        binding.bottomNavigationViewStudent.menu.getItem(1).isEnabled = false
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_controller) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationViewStudent.setupWithNavController(navController)

        binding.fabStudent.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

}
