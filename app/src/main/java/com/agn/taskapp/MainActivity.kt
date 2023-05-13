package com.agn.taskapp

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.agn.taskapp.data.local.Pref
import com.agn.taskapp.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var pref: Pref
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        pref = Pref(this)
        val navView: BottomNavigationView = binding.navView
        auth = FirebaseAuth.getInstance()

        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        if (!pref.isUserSeen())
            navController.navigate(R.id.onBoardingFragment)

        if (auth.currentUser?.uid== null) {
            navController.navigate(R.id.authFragment)
        }

        val bottomNavFragments = setOf(
            R.id.navigation_home,
            R.id.navigation_dashboard,
            R.id.navigation_notifications,
            R.id.profileFragment
        )

        val appBarConfiguration = AppBarConfiguration(bottomNavFragments)

        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            if (bottomNavFragments.contains(destination.id)) {
                navView.isVisible = true
                supportActionBar?.show()
            } else {
                navView.isVisible = false
                supportActionBar?.hide()
            }
        }

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}