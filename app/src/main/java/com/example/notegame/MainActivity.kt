package com.example.notegame

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.notegame.databinding.ActivityMainBinding
import com.example.notegame.ui.base.BaseActivity
import com.example.notegame.utils.ext.hide
import com.example.notegame.utils.ext.show
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private lateinit var navController: NavController

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpNavigation()
        hideBottomNavigation()
    }

    private fun setUpNavigation(){
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main)
        binding.navView.setupWithNavController(navController)
    }
    private fun hideBottomNavigation(){
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.navigation_home -> binding.navView.show()
                R.id.detailFragment -> binding.navView.hide()
            }
        }
    }

    override fun onNavigateUp(): Boolean = navController.navigateUp() || super.onSupportNavigateUp()

}