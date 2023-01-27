package com.kaankarakas.booktime.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import com.kaankarakas.booktime.R
import com.kaankarakas.booktime.databinding.ActivityAppBinding

class AppActivity : AppCompatActivity() {
    private var _binding: ActivityAppBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager
            .findFragmentById(binding.navHostFragment2.id) as NavHostFragment

        val navController = navHostFragment.navController

        NavigationUI.setupWithNavController(binding.navView, navController)

        val builder = AppBarConfiguration.Builder(navController.graph)
        builder.setOpenableLayout(binding.drawerLayout)

        val appBarConfiguration = builder.build()
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)




        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.profileFragment) {
                binding.toolbar.visibility = View.GONE
            } else {
                binding.toolbar.visibility = View.VISIBLE
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment2)
        return item.onNavDestinationSelected(navController)
                || super.onOptionsItemSelected(item)
    }

}