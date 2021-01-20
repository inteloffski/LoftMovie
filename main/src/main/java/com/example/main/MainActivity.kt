package com.example.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.core_api.providers.AppWithFacade
import com.example.di.MainActivityComponent
import com.example.main.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        initDI()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return

        initNavigation()

        val navController = host.navController
        setupBottomNav(navController)




    }
    private fun setupBottomNav(navController: NavController) {
        binding.bottomNavView?.setupWithNavController(navController)

    }
    private fun initDI() {
        MainActivityComponent.create((application as AppWithFacade).getFacade())
            .inject(this)
    }

    private fun initNavigation() {

        val navController = findNavController(R.id.nav_host_fragment)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.splashFragment -> {
                    binding.bottomNavView.visibility = View.GONE
                }
                else -> {
                    binding.bottomNavView.visibility = View.VISIBLE

                }

            }
        }

    }



}