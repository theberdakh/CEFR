package com.imax.cefr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.imax.cefr.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.activity_container_view) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val splashGraph = inflater.inflate(R.navigation.splash_nav)
        navHostFragment.navController.graph = splashGraph


    }

}
