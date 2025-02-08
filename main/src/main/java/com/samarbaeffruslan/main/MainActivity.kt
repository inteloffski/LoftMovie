package com.samarbaeffruslan.main

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.samarbaeffruslan.core_api.providers.AppWithFacade
import com.samarbaeffruslan.di.MainActivityComponent
import com.samarbaeffruslan.main.databinding.ActivityMainBinding
import com.samarbaeffruslan.asset.R as Asset

const val TAG = "MainActivity"

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(Asset.color.dark_purple));
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
        val navController = findNavController(R.id.nav_host_fragment)

        val currentId = navController.currentDestination?.id

        when (currentId) {
            R.id.popularFragment -> {
                Log.d(TAG, "PopularFragmentBefore")
                showExitApplicationDialog()
            }
            R.id.favoriteFragment -> {
                Log.d(TAG, "FavoriteFragment")
                navController.navigate(R.id.popularFragment)
            }
            R.id.searchFragment -> {
                Log.d(TAG, "SearchFragment")
                navController.navigate(R.id.popularFragment)
            }
            R.id.detailFragment -> {
                Log.d(TAG, "DetailFragment")
                navController.navigate(R.id.popularFragment)
            }
        }
    }


    private fun setupBottomNav(navController: NavController) {
        binding.bottomNavView.setupWithNavController(navController)

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

    private fun showExitApplicationDialog(){
        val builder = AlertDialog.Builder(this)
        builder.setMessage(Asset.string.alert_dialog_message)
            .setPositiveButton(Asset.string.alert_dialog_positive_button) { dialog, which ->
                finish()
            }
            .setNegativeButton(Asset.string.alert_dialog_negative_button) {dialog, which ->
                dialog.dismiss()
            }
        builder.create()
        builder.show()
    }


}